package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.callback.ObjectMappingRenderCallback;
import com.eltech.olap.demo.domain.Table;
import com.eltech.olap.demo.service.PivotModelService;
import org.pivot4j.PivotModel;
import org.pivot4j.ui.table.TableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PivotModelServiceImpl implements PivotModelService{

    private final PivotModel pivotModel;

    @Autowired
    public PivotModelServiceImpl(PivotModel pivotModel) {
        this.pivotModel = pivotModel;
    }

    @Override
    public Table getQueryResult(String mdxQuery) {
        runMdx(mdxQuery);

        ObjectMappingRenderCallback callback = new ObjectMappingRenderCallback();

        TableRenderer renderer = new TableRenderer();
        renderer.setShowDimensionTitle(false); // Optionally hide the dimension title headers.
        renderer.setShowParentMembers(true); // Optionally make the parent members visible.

        renderer.render(pivotModel, callback); // Render the result as a HTML page.

        return callback.getTable();
    }

    private void runMdx(String mdxQuery) {
        if(!pivotModel.isInitialized()){
            pivotModel.setMdx(mdxQuery);
            pivotModel.initialize();
        } else{
            pivotModel.setMdx(mdxQuery);
            pivotModel.getCellSet();
        }
    }
}
