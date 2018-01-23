package com.eltech.olap.demo.pivot;

import com.eltech.olap.demo.domain.Table;
import org.olap4j.OlapDataSource;
import org.pivot4j.impl.PivotModelImpl;

public class CustomPivotModelImpl extends PivotModelImpl implements CustomPivotModel {
    private Table table;

    public CustomPivotModelImpl(OlapDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public Table getTable() {
        return table;
    }
}
