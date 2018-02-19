package com.eltech.olap.demo.service;

import org.pivot4j.PivotModel;

public interface HierarchyService {
    void addHierarchy(PivotModel pivotModel, String hierarchyName);

    void moveHierarchy(PivotModel pivotModel, String hierarchyName, Integer position);

    void removeHierarchy(PivotModel pivotModel, String hierarchyName);
}
