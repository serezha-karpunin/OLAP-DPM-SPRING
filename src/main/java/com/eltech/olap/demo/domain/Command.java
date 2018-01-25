package com.eltech.olap.demo.domain;

import org.pivot4j.ui.command.UICommandParameters;

public class Command {
    private String name;
    private String mode;
    private UICommandParameters parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public UICommandParameters getParameters() {
        return parameters;
    }

    public void setParameters(UICommandParameters parameters) {
        this.parameters = parameters;
    }
}
