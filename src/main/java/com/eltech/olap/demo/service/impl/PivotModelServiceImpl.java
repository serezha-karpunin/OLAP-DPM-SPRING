package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.domain.meta.AxisMetadata;
import com.eltech.olap.demo.domain.meta.HierarchyMetadata;
import com.eltech.olap.demo.domain.meta.PivotMetadata;
import com.eltech.olap.demo.service.PivotModelService;
import org.olap4j.Axis;
import org.pivot4j.PivotModel;
import org.pivot4j.datasource.SimpleOlapDataSource;
import org.pivot4j.impl.PivotModelImpl;
import org.pivot4j.transform.PlaceHierarchiesOnAxes;
import org.pivot4j.ui.command.UICommand;
import org.pivot4j.ui.command.UICommandParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PivotModelServiceImpl implements PivotModelService {

    private final SimpleOlapDataSource simpleOlapDataSource;

    @Autowired
    public PivotModelServiceImpl(SimpleOlapDataSource simpleOlapDataSource) {
        this.simpleOlapDataSource = simpleOlapDataSource;
    }

    @Override
    public PivotModel executeMdxQuery(String mdxQuery) {
        return createPivotModel(mdxQuery);
    }

    @Override
    public PivotModel executeCommand(String storedQuery, UICommand<?> uiCommand, UICommandParameters commandParameters) {
        PivotModel model = createPivotModel(storedQuery);
        uiCommand.execute(model, commandParameters);
        return model;
    }

    public PivotMetadata getMetadata(PivotModel model) {
        PlaceHierarchiesOnAxes transform = model.getTransform(PlaceHierarchiesOnAxes.class);
        List<HierarchyMetadata> columnHierarchies = transform.findVisibleHierarchies(Axis.COLUMNS)
                .stream()
                .map(h -> new HierarchyMetadata(h.getName()))
                .collect(Collectors.toList());

        List<HierarchyMetadata> rowHierarchies = transform.findVisibleHierarchies(Axis.ROWS)
                .stream()
                .map(h -> new HierarchyMetadata(h.getName()))
                .collect(Collectors.toList());

        List<String> tempNames = new ArrayList();
        tempNames.addAll(columnHierarchies
                .stream()
                .map(HierarchyMetadata::getName)
                .collect(Collectors.toList()));
        tempNames.addAll(rowHierarchies
                .stream()
                .map(HierarchyMetadata::getName)
                .collect(Collectors.toList()));

        List<HierarchyMetadata> possibleHierarchies = model.getCube().getHierarchies()
                .stream()
                .filter(h -> !tempNames.contains(h.getName()))
                .map(h -> new HierarchyMetadata(h.getName()))
                .collect(Collectors.toList());

        PivotMetadata pivotMetadata = new PivotMetadata();
        pivotMetadata.setRowAxis(new AxisMetadata(Axis.Standard.ROWS.name(), rowHierarchies));
        pivotMetadata.setColumnAxis(new AxisMetadata(Axis.Standard.COLUMNS.name(), columnHierarchies));
        pivotMetadata.setPossibleHierarchies(possibleHierarchies);
        return pivotMetadata;
    }

    public PivotModel createPivotModel(String mdxQuery) {
        PivotModel model = new PivotModelImpl(simpleOlapDataSource);
        model.setMdx(mdxQuery);
        model.initialize();

        return model;
    }
}
