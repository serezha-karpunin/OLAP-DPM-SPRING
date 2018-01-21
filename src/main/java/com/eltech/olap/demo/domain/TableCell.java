package com.eltech.olap.demo.domain;

public class TableCell {
    private String type;
    private int padding;
    private Double value;
    private String label;

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
