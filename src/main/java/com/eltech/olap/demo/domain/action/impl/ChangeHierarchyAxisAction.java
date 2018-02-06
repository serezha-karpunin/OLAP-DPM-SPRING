package com.eltech.olap.demo.domain.action.impl;

import com.eltech.olap.demo.domain.action.AbstractHierarchyAction;

public class ChangeHierarchyAxisAction extends AbstractHierarchyAction {
    private String sourceAxisName;
    private Integer position;

    public ChangeHierarchyAxisAction(String targetAxisName, String hierarchyName, String sourceAxisName, Integer position) {
        super(targetAxisName, hierarchyName);
        this.sourceAxisName = sourceAxisName;
        this.position = position;
    }

    public String getSourceAxisName() {
        return sourceAxisName;
    }

    public void setSourceAxisName(String sourceAxisName) {
        this.sourceAxisName = sourceAxisName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
