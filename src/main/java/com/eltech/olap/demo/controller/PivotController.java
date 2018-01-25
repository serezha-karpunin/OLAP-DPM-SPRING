package com.eltech.olap.demo.controller;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.QueryResult;
import com.eltech.olap.demo.service.PivotModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@Controller
public class PivotController {

    private final String CURRENT_QUERY_ATTRIBUTE_NAME = "currentQuery";
    private final PivotModelService pivotModelService;

    @Autowired
    public PivotController(PivotModelService pivotModelService) {
        this.pivotModelService = pivotModelService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mdx")
    public ResponseEntity<QueryResult> executeMdxQuery(@RequestParam(value = "query") String mdxQuery, HttpServletRequest request) {
        QueryResult queryResult = pivotModelService.executeMdxQuery(mdxQuery);

        request.getSession().setAttribute(CURRENT_QUERY_ATTRIBUTE_NAME, queryResult.getQuery());
        return new ResponseEntity<>(queryResult, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/execute")
    public ResponseEntity<QueryResult> executeCommand(HttpServletRequest request, @RequestBody Command command) {
        String storedQuery = (String) request.getSession().getAttribute(CURRENT_QUERY_ATTRIBUTE_NAME);

        QueryResult queryResult = pivotModelService.executeCommand(storedQuery, command);

        request.getSession().setAttribute(CURRENT_QUERY_ATTRIBUTE_NAME, queryResult.getQuery());
        return new ResponseEntity<>(queryResult, HttpStatus.OK);
    }
}
