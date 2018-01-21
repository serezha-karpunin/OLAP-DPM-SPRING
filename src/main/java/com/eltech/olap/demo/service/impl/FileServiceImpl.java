package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.service.FileService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;


@Service
public class FileServiceImpl implements FileService {

    // TODO: 21.01.2018 FIX ABSOLUTE PATH
    private String SCHEMA_FILE_PATH = "C:\\Users\\Admin\\Desktop\\olap-dpm\\src\\main\\resources\\Diploma.xml";

    @Override
    public String getSchemaContent() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(SCHEMA_FILE_PATH)));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();

        return sb.toString();
    }

    @Override
    public void writeSchema(String schema) throws IOException {
        Files.deleteIfExists(new File(SCHEMA_FILE_PATH).toPath());

        File file = new File(SCHEMA_FILE_PATH);
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(schema);
        fileWriter.close();
    }
}
