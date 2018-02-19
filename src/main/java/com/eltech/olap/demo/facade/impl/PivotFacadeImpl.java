package com.eltech.olap.demo.facade.impl;

import com.eltech.olap.demo.callback.ObjectMappingRenderCallback;
import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.PivotTableState;
import com.eltech.olap.demo.domain.action.impl.AddHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.ChangeHierarchyAxisAction;
import com.eltech.olap.demo.domain.action.impl.MoveHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.RemoveHierarchyAction;
import com.eltech.olap.demo.facade.PivotFacade;
import com.eltech.olap.demo.service.HierarchyService;
import com.eltech.olap.demo.service.PivotModelService;
import org.pivot4j.PivotModel;
import org.pivot4j.transform.NonEmpty;
import org.pivot4j.ui.command.UICommand;
import org.pivot4j.ui.table.TableRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PivotFacadeImpl implements PivotFacade {

    private final PivotModelService pivotModelService;

    private final HierarchyService hierarchyService;

    @Autowired
    public PivotFacadeImpl(PivotModelService pivotModelService, HierarchyService hierarchyService) {
        this.pivotModelService = pivotModelService;
        this.hierarchyService = hierarchyService;
    }

    @Override
    public PivotTableState executeMdxQuery(String mdxQuery, Boolean showDimensionTitle, Boolean showParentMembers) {
        PivotModel model = pivotModelService.executeMdxQuery(mdxQuery);
        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    @Override
    public PivotTableState executeCommand(String mdxQuery, Command command, Boolean showDimensionTitle, Boolean showParentMembers) {
        UICommand<?> uiCommand = getTableRenderer(showDimensionTitle,showParentMembers).getCommand(command.getName());
        PivotModel model = pivotModelService.executeCommand(mdxQuery, uiCommand, command.getParameters());

        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    @Override
    public PivotTableState addHierarchy(String mdxQuery, AddHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers) {
        PivotModel model = pivotModelService.createPivotModel(mdxQuery);
        hierarchyService.addHierarchy(model, action);

        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    @Override
    public PivotTableState moveHierarchy(String mdxQuery, MoveHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers) {
        PivotModel model = pivotModelService.createPivotModel(mdxQuery);
        hierarchyService.moveHierarchy(model, action);

        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    @Override
    public PivotTableState removeHierarchy(String mdxQuery, RemoveHierarchyAction action, Boolean showDimensionTitle, Boolean showParentMembers) {
        PivotModel model = pivotModelService.createPivotModel(mdxQuery);
        hierarchyService.removeHierarchy(model, action);

        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    @Override
    public PivotTableState changeHierarchyAxis(String mdxQuery, ChangeHierarchyAxisAction action, Boolean showDimensionTitle, Boolean showParentMembers) {
        PivotModel model = pivotModelService.createPivotModel(mdxQuery);
        hierarchyService.changeHierarchyAxis(model, action);

        return getPivotTableState(model, showDimensionTitle, showParentMembers);
    }

    private PivotTableState getPivotTableState(PivotModel model, Boolean showDimensionTitle, Boolean showParentMembers) {
        model.getTransform(NonEmpty.class).setNonEmpty(true);

        ObjectMappingRenderCallback callback = new ObjectMappingRenderCallback();
        getTableRenderer(showDimensionTitle, showParentMembers).render(model, callback);

        return new PivotTableState(callback.getTable(), model.getCurrentMdx(), pivotModelService.getMetadata(model));
    }

    private TableRenderer getTableRenderer(Boolean showDimensionTitle, Boolean showParentMembers) {
        TableRenderer renderer = new TableRenderer();
        renderer.setShowDimensionTitle(showDimensionTitle);
        renderer.setShowParentMembers(showParentMembers);

        return renderer;
    }


}
