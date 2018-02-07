package com.eltech.olap.demo.domain.meta;

import java.util.List;

public class PivotMetadata {
    private List<HierarchyMetadata> possibleHierarchies;
    private AxisMetadata columnAxis;
    private AxisMetadata rowAxis;

    public List<HierarchyMetadata> getPossibleHierarchies() {
        return possibleHierarchies;
    }

    public void setPossibleHierarchies(List<HierarchyMetadata> possibleHierarchies) {
        this.possibleHierarchies = possibleHierarchies;
    }

    public AxisMetadata getColumnAxis() {
        return columnAxis;
    }

    public void setColumnAxis(AxisMetadata columnAxis) {
        this.columnAxis = columnAxis;
    }

    public AxisMetadata getRowAxis() {
        return rowAxis;
    }

    public void setRowAxis(AxisMetadata rowAxis) {
        this.rowAxis = rowAxis;
    }
}
