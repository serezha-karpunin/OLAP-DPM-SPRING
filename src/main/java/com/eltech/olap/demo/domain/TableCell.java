package com.eltech.olap.demo.domain;

import java.util.List;

public class TableCell {
    private String type;
    private int padding;
    private Double value;
    private String label;

    private int colSpan;

    private List<Command> commands;

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public int getColSpan() {
        return colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    private int rowSpan;

    public void setType(String type) {
        this.type = type;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public int getPadding() {
        return padding;
    }

    public Double getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
