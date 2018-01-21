package com.eltech.olap.demo.controller;

import com.eltech.olap.demo.domain.Table;
import com.eltech.olap.demo.service.PivotModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("*")
@Controller
public class PivotController {

    // TODO: 21.01.2018 add saving model state to session
//    private final String PIVOT_STATE_ATTRIBUTE_NAME = "pivotState";

    private final PivotModelService pivotModelService;

    @Autowired
    public PivotController(PivotModelService pivotModelService) {
        this.pivotModelService = pivotModelService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mdx")
    public ResponseEntity<Table> getQueryResult(@RequestParam(value = "query") String mdxQuery) {
        return new ResponseEntity<>(pivotModelService.getQueryResult(mdxQuery), HttpStatus.OK);
    }
}
