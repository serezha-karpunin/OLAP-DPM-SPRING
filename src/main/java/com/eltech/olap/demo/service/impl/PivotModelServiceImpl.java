package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.callback.ObjectMappingRenderCallback;
import com.eltech.olap.demo.pivot.CustomPivotModel;
import com.eltech.olap.demo.pivot.CustomPivotModelImpl;
import com.eltech.olap.demo.service.PivotModelService;
import org.pivot4j.datasource.SimpleOlapDataSource;
import org.pivot4j.ui.table.TableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class PivotModelServiceImpl implements PivotModelService{

    @Autowired
    private SimpleOlapDataSource simpleOlapDataSource;

    @Override
    public CustomPivotModel executeMdxQuery(Serializable bookmark, String mdxQuery) {
        CustomPivotModel model = new CustomPivotModelImpl(simpleOlapDataSource);

        if(bookmark != null){
            model.restoreState(bookmark);
            model.setMdx(mdxQuery);
            model.getCellSet();
        } else{
            model.setMdx(mdxQuery);
            model.initialize();
        }

        ObjectMappingRenderCallback callback = new ObjectMappingRenderCallback();

        TableRenderer renderer = new TableRenderer();
        renderer.setShowDimensionTitle(false); // Optionally hide the dimension title headers.
        renderer.setShowParentMembers(true); // Optionally make the parent members visible.

        renderer.render(model, callback); // Render the result as a HTML page.
        model.setTable(callback.getTable());

        return model;
    }



}
