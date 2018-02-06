package com.eltech.olap.demo.service;

import com.eltech.olap.demo.domain.action.impl.AddHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.ChangeHierarchyAxisAction;
import com.eltech.olap.demo.domain.action.impl.MoveHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.RemoveHierarchyAction;
import org.pivot4j.PivotModel;

public interface HierarchyService {
    void addHierarchy(PivotModel pivotModel, AddHierarchyAction action);
    void moveHierarchy(PivotModel pivotModel, MoveHierarchyAction action);
    void removeHierarchy(PivotModel pivotModel, RemoveHierarchyAction action);
    void changeHierarchyAxis(PivotModel pivotModel, ChangeHierarchyAxisAction action);
}
