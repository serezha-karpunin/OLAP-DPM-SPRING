package com.eltech.olap.demo.facade;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.PivotTableState;

public interface PivotFacade {
    PivotTableState executeMdxQuery(String mdxQuery, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState executeCommand(String mdxQuery, Command command, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState addHierarchy(String mdxQuery, String hierarchyName, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState moveHierarchy(String mdxQuery, String hierarchyName, Integer position, Boolean showDimensionTitle, Boolean showParentMembers);

    PivotTableState removeHierarchy(String mdxQuery, String hierarchyName, Boolean showDimensionTitle, Boolean showParentMembers);

}
