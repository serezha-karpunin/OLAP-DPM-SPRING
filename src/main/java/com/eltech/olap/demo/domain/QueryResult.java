package com.eltech.olap.demo.domain;

import com.eltech.olap.demo.domain.table.Table;

public class QueryResult {

    private Table table;
    private String query;

    public QueryResult(Table table, String query) {
        this.table = table;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
