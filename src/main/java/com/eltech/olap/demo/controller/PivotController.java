package com.eltech.olap.demo.controller;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.PivotTableState;
import com.eltech.olap.demo.domain.action.impl.AddHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.ChangeHierarchyAxisAction;
import com.eltech.olap.demo.domain.action.impl.MoveHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.RemoveHierarchyAction;
import com.eltech.olap.demo.facade.PivotFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
public class PivotController {

    private final String CURRENT_QUERY_ATTRIBUTE_NAME = "currentQuery";

    private final PivotFacade pivotFacade;

    @Autowired
    public PivotController(PivotFacade pivotFacade) {
        this.pivotFacade = pivotFacade;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mdx")
    public ResponseEntity<PivotTableState> executeMdxQuery(@RequestParam(value = "query") String mdxQuery,
                                                           Boolean showDimensionTitle,
                                                           Boolean showParentMembers,
                                                           HttpServletRequest request) {
        PivotTableState pivotTableState = pivotFacade.executeMdxQuery(mdxQuery, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/execute")
    public ResponseEntity<PivotTableState> executeCommand(@RequestBody Command command,
                                                          Boolean showDimensionTitle,
                                                          Boolean showParentMembers,
                                                          HttpServletRequest request) {
        PivotTableState pivotTableState = pivotFacade
                .executeCommand(restoreQuery(request), command, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hierarchy/add")
    public ResponseEntity<PivotTableState> addHierarchyAxis(@RequestBody AddHierarchyAction action,
                                                            Boolean showDimensionTitle,
                                                            Boolean showParentMembers,
                                                            HttpServletRequest request) {
        PivotTableState pivotTableState =
                pivotFacade.addHierarchy(restoreQuery(request), action, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hierarchy/remove")
    public ResponseEntity<PivotTableState> removeHierarchyAxis(@RequestBody RemoveHierarchyAction action,
                                                               Boolean showDimensionTitle,
                                                               Boolean showParentMembers,
                                                               HttpServletRequest request) {
        PivotTableState pivotTableState =
                pivotFacade.removeHierarchy(restoreQuery(request), action, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hierarchy/move")
    public ResponseEntity<PivotTableState> moveHierarchyAxis(@RequestBody MoveHierarchyAction action,
                                                             Boolean showDimensionTitle,
                                                             Boolean showParentMembers,
                                                             HttpServletRequest request) {
        PivotTableState pivotTableState =
                pivotFacade.moveHierarchy(restoreQuery(request), action, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/hierarchy/change")
    public ResponseEntity<PivotTableState> changeHierarchyAxis(@RequestBody ChangeHierarchyAxisAction action,
                                                               Boolean showDimensionTitle,
                                                               Boolean showParentMembers,
                                                               HttpServletRequest request) {
        PivotTableState pivotTableState =
                pivotFacade.changeHierarchyAxis(restoreQuery(request), action, showDimensionTitle, showParentMembers);
        storeQuery(request, pivotTableState.getQuery());
        return new ResponseEntity<>(pivotTableState, HttpStatus.OK);
    }

    private String restoreQuery(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(CURRENT_QUERY_ATTRIBUTE_NAME);
    }

    private void storeQuery(HttpServletRequest request, String query) {
        request.getSession().setAttribute(CURRENT_QUERY_ATTRIBUTE_NAME, query);
    }
}
