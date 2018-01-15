package com.eltech.olap.demo.controller;

import org.olap4j.CellSet;
import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.OlapStatement;
import org.olap4j.layout.RectangularCellSetFormatter;
import org.olap4j.mdx.SelectNode;
import org.olap4j.mdx.parser.MdxParser;
import org.olap4j.mdx.parser.MdxParserFactory;
import org.olap4j.mdx.parser.MdxValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;

@CrossOrigin("*")
@Controller
public class DBController {
    private String FILE_PATH = "C:\\Users\\Admin\\Desktop\\olap-dpm\\src\\main\\resources\\Diploma.xml";

    private final OlapConnection olapConnection;

    @Autowired
    public DBController(OlapConnection olapConnection) {
        this.olapConnection = olapConnection;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/files")
    public void getXmlSchemaFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "inline;filename=schema.xml");

        BufferedReader br = new BufferedReader(new FileReader(new File(FILE_PATH)));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        ServletOutputStream outStream = response.getOutputStream();
        outStream.println(sb.toString());
        outStream.flush();
        outStream.close();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/files", consumes = "application/xml")
    public ResponseEntity<Object> postXmlSchemaFile(@RequestBody String body) throws IOException {
        Files.deleteIfExists(new File(FILE_PATH).toPath());
        File file = new File(FILE_PATH);
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(body);
            fileWriter.close();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mdx")
    public ResponseEntity<String> query(@RequestParam("query") String mdxQuery) {

        MdxParserFactory pFactory = olapConnection.getParserFactory();
        MdxParser parser = pFactory.createMdxParser(olapConnection);
        SelectNode parsedObject = parser.parseSelect(mdxQuery);

        MdxValidator validator =
                pFactory.createMdxValidator(olapConnection);
        try {
            validator.validateSelect(parsedObject);
        } catch (OlapException e) {
            System.out.println(e.getMessage());
        }

        try {
            OlapStatement olapStatement = olapConnection.createStatement();
            CellSet cellSet = olapStatement.executeOlapQuery(mdxQuery);

            final RectangularCellSetFormatter formatter = new RectangularCellSetFormatter(false);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            final PrintWriter writer = new PrintWriter(os);
            formatter.format(cellSet, writer);
            writer.flush();

            String aString = new String(os.toByteArray(),"UTF-8");
            return new ResponseEntity<>(aString, HttpStatus.OK);

        } catch (OlapException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
