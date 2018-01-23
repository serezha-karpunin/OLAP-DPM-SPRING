package com.eltech.olap.demo.pivot;

import com.eltech.olap.demo.domain.Table;
import org.pivot4j.PivotModel;

public interface CustomPivotModel extends PivotModel {
    void setTable(Table table);

    Table getTable();
}
