package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class AgendaDia implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String label;
    private Date dia;

    public AgendaDia() {
        super();
    }

    public AgendaDia(Integer id, String label, Date dia) {
        super();
        this.id = id;
        this.label = label;
        this.dia = dia;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
