package com.eltech.olap.demo.domain.action.impl;

import com.eltech.olap.demo.domain.action.AbstractHierarchyAction;

public class RemoveHierarchyAction extends AbstractHierarchyAction {
    public RemoveHierarchyAction() {
    }

    public RemoveHierarchyAction(String targetAxisName, String hierarchyName) {
        super(targetAxisName, hierarchyName);
    }
}

