package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.domain.action.impl.AddHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.ChangeHierarchyAxisAction;
import com.eltech.olap.demo.domain.action.impl.MoveHierarchyAction;
import com.eltech.olap.demo.domain.action.impl.RemoveHierarchyAction;
import com.eltech.olap.demo.service.HierarchyService;
import org.olap4j.Axis;
import org.olap4j.metadata.Hierarchy;
import org.pivot4j.PivotModel;
import org.pivot4j.transform.PlaceHierarchiesOnAxes;
import org.springframework.stereotype.Service;

@Service
public class HierarchyServiceImpl implements HierarchyService {

    @Override
    public void addHierarchy(PivotModel pivotModel, AddHierarchyAction action) {
        getTransform(pivotModel).addHierarchy(
                getAxis(action.getTargetAxisName()),
                getHierarchy(pivotModel, action.getHierarchyName()),
                false,
                action.getPosition());
    }

    @Override
    public void moveHierarchy(PivotModel pivotModel, MoveHierarchyAction action) {
        getTransform(pivotModel).moveHierarchy(
                getAxis(action.getTargetAxisName()),
                getHierarchy(pivotModel, action.getHierarchyName()),
                action.getPosition());

    }

    @Override
    public void removeHierarchy(PivotModel pivotModel, RemoveHierarchyAction action) {
        getTransform(pivotModel).removeHierarchy(
                getAxis(action.getTargetAxisName()),
                getHierarchy(pivotModel, action.getHierarchyName()));
    }

    @Override
    public void changeHierarchyAxis(PivotModel pivotModel, ChangeHierarchyAxisAction action) {
        removeHierarchy(pivotModel, new RemoveHierarchyAction(action.getSourceAxisName(), action.getHierarchyName()));
        addHierarchy(pivotModel, new AddHierarchyAction(action.getTargetAxisName(), action.getHierarchyName(), action.getPosition()));
    }

    private PlaceHierarchiesOnAxes getTransform(PivotModel model) {
        return model.getTransform(PlaceHierarchiesOnAxes.class);
    }

    private Axis getAxis(String axisName) {
        return Axis.Standard.valueOf(axisName);
    }

    private Hierarchy getHierarchy(PivotModel pivotModel, String hierarchyName) {
        return pivotModel.getCube().getHierarchies().get(hierarchyName);
    }

}
