package com.eltech.olap.demo.domain;

import com.eltech.olap.demo.domain.meta.PivotMetadata;
import com.eltech.olap.demo.domain.table.Table;

public class PivotTableState {

    private Table table;
    private String query;
    private PivotMetadata metadata;

    public PivotTableState(Table table, String query, PivotMetadata metadata) {
        this.table = table;
        this.query = query;
        this.metadata = metadata;
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

    public PivotMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(PivotMetadata metadata) {
        this.metadata = metadata;
    }
}
