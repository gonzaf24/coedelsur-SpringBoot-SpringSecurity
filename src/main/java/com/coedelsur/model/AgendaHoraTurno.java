package com.coedelsur.model;

import java.io.Serializable;
import java.util.Date;

public class AgendaHoraTurno implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date horaDesde;
    private String labelHoraDesde;
    private Date horaHasta;
    private String labelHoraHasta;
    private SelectStringValue intervalo;

    public AgendaHoraTurno() {
        super();
    }

    public AgendaHoraTurno(Date horaDesde, Date horaHasta, SelectStringValue intervalo) {
        super();
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
        this.intervalo = intervalo;
    }

    public AgendaHoraTurno(Date horaDesde, String labelHoraDesde, Date horaHasta, String labelHoraHasta, SelectStringValue intervalo) {
        super();
        this.horaDesde = horaDesde;
        this.labelHoraDesde = labelHoraDesde;
        this.horaHasta = horaHasta;
        this.labelHoraHasta = labelHoraHasta;
        this.intervalo = intervalo;
    }

    public String getLabelHoraDesde() {
        return labelHoraDesde;
    }

    public void setLabelHoraDesde(String labelHoraDesde) {
        this.labelHoraDesde = labelHoraDesde;
    }

    public String getLabelHoraHasta() {
        return labelHoraHasta;
    }

    public void setLabelHoraHasta(String labelHoraHasta) {
        this.labelHoraHasta = labelHoraHasta;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public SelectStringValue getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(SelectStringValue intervalo) {
        this.intervalo = intervalo;
    }
}
