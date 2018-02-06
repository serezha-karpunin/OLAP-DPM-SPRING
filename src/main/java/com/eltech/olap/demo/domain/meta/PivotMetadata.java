package com.eltech.olap.demo.domain.meta;

import java.util.List;

public class PivotMetadata {
    private List<HierarchyMetadata> possibleHierarchies;
    private List<HierarchyMetadata> columnHierarchies;
    private List<HierarchyMetadata> rowHierarchies;

    public List<HierarchyMetadata> getPossibleHierarchies() {
        return possibleHierarchies;
    }

    public void setPossibleHierarchies(List<HierarchyMetadata> possibleHierarchies) {
        this.possibleHierarchies = possibleHierarchies;
    }

    public List<HierarchyMetadata> getColumnHierarchies() {
        return columnHierarchies;
    }

    public void setColumnHierarchies(List<HierarchyMetadata> columnHierarchies) {
        this.columnHierarchies = columnHierarchies;
    }

    public List<HierarchyMetadata> getRowHierarchies() {
        return rowHierarchies;
    }

    public void setRowHierarchies(List<HierarchyMetadata> rowHierarchies) {
        this.rowHierarchies = rowHierarchies;
    }
}
