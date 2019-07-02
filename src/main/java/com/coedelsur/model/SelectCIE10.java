package com.coedelsur.model;

import java.io.Serializable;

public class SelectCIE10 implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id10;
    private String dec10;
    private String grp10;

    public SelectCIE10() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SelectCIE10(String id10, String dec10, String grp10) {
        super();
        this.id10 = id10;
        this.dec10 = dec10;
        this.grp10 = grp10;
    }

    public String getId10() {
        return id10;
    }

    public void setId10(String id10) {
        this.id10 = id10;
    }

    public String getDec10() {
        return dec10;
    }

    public void setDec10(String dec10) {
        this.dec10 = dec10;
    }

    public String getGrp10() {
        return grp10;
    }

    public void setGrp10(String grp10) {
        this.grp10 = grp10;
    }
    
    public String getLabel() {
        return id10 + " - " + dec10 ;
    }
}
