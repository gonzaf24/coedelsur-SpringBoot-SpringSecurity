package com.coedelsur.model;

import java.io.Serializable;

public class SelectStringString implements Serializable {

    private static final long serialVersionUID = 1L;
    private String label;
    private String code;
    private Boolean activo;

    public SelectStringString(String label, String code) {
        super();
        this.label = label;
        this.code = code;
    }

    public SelectStringString(String label, String code, Boolean activo) {
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

    public SelectStringString() {
        super();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
