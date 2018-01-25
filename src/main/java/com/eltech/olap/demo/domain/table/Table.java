package com.eltech.olap.demo.domain.table;

public class Table {
    private TableHeader header;
    private TableBody body;

    public TableHeader getHeader() {
        return header;
    }

    public void setHeader(TableHeader header) {
        this.header = header;
    }

    public TableBody getBody() {
        return body;
    }

    public void setBody(TableBody body) {
        this.body = body;
    }
}
