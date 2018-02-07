package com.eltech.olap.demo.domain.meta;

import java.util.List;

public class AxisMetadata {
    private String name;
    private List<HierarchyMetadata> hierarchies;

    public AxisMetadata(String name, List<HierarchyMetadata> hierarchies) {
        this.name = name;
        this.hierarchies = hierarchies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HierarchyMetadata> getHierarchies() {
        return hierarchies;
    }

    public void setHierarchies(List<HierarchyMetadata> hierarchies) {
        this.hierarchies = hierarchies;
    }
}
