package com.eltech.olap.demo.controller;


import com.eltech.olap.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin("*")
@Controller
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/files")
    public void getXmlSchemaFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "inline;filename=schema.xml");

        String xmlSchema = fileService.getSchemaContent();

        ServletOutputStream outStream = response.getOutputStream();
        outStream.println(xmlSchema);
        outStream.flush();
        outStream.close();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/files", consumes = "application/xml")
    public ResponseEntity<Object> postXmlSchemaFile(@RequestBody String body) throws IOException {
        fileService.writeSchema(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
