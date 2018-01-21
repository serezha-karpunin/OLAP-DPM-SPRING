package com.eltech.olap.demo.service;

import java.io.IOException;

public interface FileService {

    String getSchemaContent() throws IOException;
    void writeSchema(String schema) throws IOException;
}
