package com.eltech.olap.demo.controller;

import com.eltech.olap.demo.domain.Table;
import com.eltech.olap.demo.service.PivotModelService;
import org.pivot4j.PivotModel;
import org.pivot4j.datasource.SimpleOlapDataSource;
import org.pivot4j.impl.PivotModelImpl;
import org.pivot4j.ui.html.HtmlRenderCallback;
import org.pivot4j.ui.table.TableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@CrossOrigin("*")
@Controller
public class PivotController {

    // TODO: 21.01.2018 add saving model state to session
//    private final String PIVOT_STATE_ATTRIBUTE_NAME = "pivotState";

    private final PivotModelService pivotModelService;

    @Autowired
    private SimpleOlapDataSource simpleOlapDataSource;

    @Autowired
    public PivotController(PivotModelService pivotModelService) {
        this.pivotModelService = pivotModelService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mdx")
    public ResponseEntity<Table> getQueryResult(@RequestParam(value = "query") String mdxQuery) {
        return new ResponseEntity<>(pivotModelService.getQueryResult(mdxQuery), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/mdx2")
    public void getQueryResult2(@RequestParam(value = "query") String mdxQuery, HttpServletResponse response) throws IOException {

        PivotModel model = new PivotModelImpl(simpleOlapDataSource);
        model.setMdx(mdxQuery);
        model.initialize();

        PrintWriter writer = response.getWriter();

        TableRenderer renderer = new TableRenderer();

        renderer.setShowDimensionTitle(false); // Optionally hide the dimension title headers.
        renderer.setShowParentMembers(true); // Optionally make the parent members visible.

        renderer.render(model, new HtmlRenderCallback(writer)); // Render the result as a HTML page.

        writer.flush();
        writer.close();
    }

}
