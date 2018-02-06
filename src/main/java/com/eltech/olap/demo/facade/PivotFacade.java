package com.eltech.olap.demo.facade;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.PivotTableState;
import com.eltech.olap.demo.domain.action.impl.AddHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.ChangeHierarchyAxisAction;
import com.eltech.olap.demo.domain.action.impl.MoveHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.RemoveHierarchyAction;

public interface PivotFacade {
    PivotTableState executeMdxQuery(String mdxQuery, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState executeCommand(String mdxQuery, Command command, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState addHierarchy(String mdxQuery, AddHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState moveHierarchy(String mdxQuery, MoveHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState removeHierarchy(String mdxQuery, RemoveHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState changeHierarchyAxis(String mdxQuery, ChangeHierarchyAxisAction action, Boolean showDimensionTitle, Boolean showParentMembers);
}
