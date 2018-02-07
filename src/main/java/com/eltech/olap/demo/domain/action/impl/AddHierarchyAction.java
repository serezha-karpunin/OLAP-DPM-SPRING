package com.eltech.olap.demo.domain.action.impl;

import com.eltech.olap.demo.domain.action.AbstractHierarchyAction;

public class AddHierarchyAction extends AbstractHierarchyAction {
    private Integer position;

    public AddHierarchyAction() {
    }

    public AddHierarchyAction(String targetAxisName, String hierarchyName, Integer position) {
        super(targetAxisName, hierarchyName);
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
