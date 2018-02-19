package com.eltech.olap.demo.service.impl;

import com.eltech.olap.demo.service.HierarchyService;
import org.olap4j.Axis;
import org.olap4j.metadata.Hierarchy;
import org.pivot4j.PivotModel;
import org.pivot4j.transform.PlaceHierarchiesOnAxes;
import org.springframework.stereotype.Service;

@Service
public class HierarchyServiceImpl implements HierarchyService {

    @Override
    public void addHierarchy(PivotModel pivotModel, String hierarchyName) {
        getTransform(pivotModel).addHierarchy(
                Axis.Standard.ROWS, getHierarchy(pivotModel, hierarchyName), false, 0);
    }

    @Override
    public void moveHierarchy(PivotModel pivotModel, String hierarchyName, Integer position) {
        getTransform(pivotModel).moveHierarchy(Axis.Standard.ROWS, getHierarchy(pivotModel, hierarchyName), position);
    }

    @Override
    public void removeHierarchy(PivotModel pivotModel, String hierarchyName) {
        getTransform(pivotModel).removeHierarchy(Axis.Standard.ROWS, getHierarchy(pivotModel, hierarchyName));
    }

    private PlaceHierarchiesOnAxes getTransform(PivotModel model) {
        return model.getTransform(PlaceHierarchiesOnAxes.class);
    }

    private Hierarchy getHierarchy(PivotModel pivotModel, String hierarchyName) {
        return pivotModel.getCube().getHierarchies().get(hierarchyName);
    }
}
