package com.coedelsur.model;

import java.io.Serializable;

public class SelectStringValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private String label;
    private Integer code;
    private Boolean activo;

    public SelectStringValue(String label, Integer code) {
        super();
        this.label = label;
        this.code = code;
    }

    public SelectStringValue(String label, Integer code, Boolean activo) {
        super();
        this.label = label;
        this.code = code;
        this.activo = activo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public SelectStringValue() {
        super();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
