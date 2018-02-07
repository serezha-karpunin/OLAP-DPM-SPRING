package com.eltech.olap.demo.domain.action;

public abstract class AbstractHierarchyAction {
    private String targetAxisName;
    private String hierarchyName;

    public AbstractHierarchyAction() {
    }

    public AbstractHierarchyAction(String targetAxisName, String hierarchyName) {
        this.targetAxisName = targetAxisName;
        this.hierarchyName = hierarchyName;
    }

    public String getTargetAxisName() {
        return targetAxisName;
    }

    public void setTargetAxisName(String targetAxisName) {
        this.targetAxisName = targetAxisName;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }
}
