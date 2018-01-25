package com.eltech.olap.demo.domain;

import org.pivot4j.ui.command.UICommandParameters;

public class Command {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String description;
    private String mode;
    private UICommandParameters parameters;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setParameters(UICommandParameters parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public String getMode() {
        return mode;
    }

    public UICommandParameters getParameters() {
        return parameters;
    }
}
