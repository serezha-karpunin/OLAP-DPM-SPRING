package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.callback.ObjectMappingRenderCallback;
import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.QueryResult;
import com.eltech.olap.demo.service.PivotModelService;
import org.pivot4j.PivotModel;
import org.pivot4j.datasource.SimpleOlapDataSource;
import org.pivot4j.impl.PivotModelImpl;
import org.pivot4j.ui.command.UICommand;
import org.pivot4j.ui.table.TableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PivotModelServiceImpl implements PivotModelService {

    private final SimpleOlapDataSource simpleOlapDataSource;

    @Autowired
    public PivotModelServiceImpl(SimpleOlapDataSource simpleOlapDataSource) {
        this.simpleOlapDataSource = simpleOlapDataSource;
    }

    @Override
    public QueryResult executeMdxQuery(String mdxQuery) {
        PivotModel model = createPivotModel(mdxQuery);

        ObjectMappingRenderCallback callback = new ObjectMappingRenderCallback();
        createTableRenderer().render(model, callback);

        return new QueryResult(callback.getTable(), model.getCurrentMdx());
    }

    @Override
    public QueryResult executeCommand(String storedQuery, Command command) {
        PivotModel model = createPivotModel(storedQuery);
        ObjectMappingRenderCallback callback = new ObjectMappingRenderCallback();
        TableRenderer tableRenderer = createTableRenderer();

        UICommand<?> uiCommand = tableRenderer.getCommand(command.getName());
        uiCommand.execute(model, command.getParameters());
        tableRenderer.render(model, callback);

        return new QueryResult(callback.getTable(), model.getCurrentMdx());
    }

    private PivotModel createPivotModel(String mdxQuery){
        PivotModel model = new PivotModelImpl(simpleOlapDataSource);
        model.setMdx(mdxQuery);
        model.initialize();

        return model;
    }

    private TableRenderer createTableRenderer(){
        TableRenderer renderer = new TableRenderer();
        renderer.setShowDimensionTitle(false);
        renderer.setShowParentMembers(true);

        return renderer;
    }
}
