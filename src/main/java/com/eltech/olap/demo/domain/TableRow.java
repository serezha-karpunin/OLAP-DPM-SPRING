package com.eltech.olap.demo.domain;

import java.util.List;

public class TableRow {

    List<DpmColumn> columns;

    public List<TableCell> getCells() {
        return cells;
    }

    public void setCells(List<TableCell> cells) {
        this.cells = cells;
    }

    List<TableCell> cells;

    public List<DpmColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DpmColumn> columns) {
        this.columns = columns;
    }
}
