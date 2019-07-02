package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Pago;
import com.coedelsur.model.SelectStringValue;

public class SolicitarCitaForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private SelectStringValue selectedTipoConsulta;
    private SelectStringValue selectedEspecialidad;
    private Doctor selectedDoctor;
    private Boolean disableStep1 = true;
    private Boolean disableStep2 = true;
    private Boolean disableStep3 = true;
    private Boolean disableStep4 = true;
    private Boolean activeStep1 = false;
    private Boolean activeStep2 = false;
    private Boolean activeStep3 = false;
    private Boolean activeStep4 = false;
    private Integer stepIndex = 0;
    private ArrayList<AgendaDia> listDiasAgendaDoctor;
    private AgendaDia selectedAgendaDia;
    private AgendaTurno selectedAgendaTurno;
    private ArrayList<AgendaTurno> listTurnosAgendaDoctor;
    private String tipoPago;
    private Pago pago;
    private Boolean cancelarConsulta = true;
    private ConsultaDoctor selectedConsulta;
    private SelectStringValue consultorioAgenda;

    public SolicitarCitaForm() {
    }

    public SelectStringValue getSelectedTipoConsulta() {
        return selectedTipoConsulta;
    }

    public void setSelectedTipoConsulta(SelectStringValue selectedTipoConsulta) {
        this.selectedTipoConsulta = selectedTipoConsulta;
    }

    public SelectStringValue getSelectedEspecialidad() {
        return selectedEspecialidad;
    }

    public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
        this.selectedEspecialidad = selectedEspecialidad;
    }

    public Doctor getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(Doctor selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public Boolean getDisableStep1() {
        return disableStep1;
    }

    public void setDisableStep1(Boolean disableStep1) {
        this.disableStep1 = disableStep1;
    }

    public Boolean getActiveStep1() {
        return activeStep1;
    }

    public void setActiveStep1(Boolean activeStep1) {
        this.activeStep1 = activeStep1;
    }

    public Boolean getActiveStep2() {
        return activeStep2;
    }

    public void setActiveStep2(Boolean activeStep2) {
        this.activeStep2 = activeStep2;
    }

    public Boolean getActiveStep3() {
        return activeStep3;
    }

    public void setActiveStep3(Boolean activeStep3) {
        this.activeStep3 = activeStep3;
    }

    public Boolean getActiveStep4() {
        return activeStep4;
    }

    public void setActiveStep4(Boolean activeStep4) {
        this.activeStep4 = activeStep4;
    }

    public Integer getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(Integer stepIndex) {
        this.stepIndex = stepIndex;
    }

    public ArrayList<AgendaDia> getListDiasAgendaDoctor() {
        return listDiasAgendaDoctor;
    }

    public void setListDiasAgendaDoctor(ArrayList<AgendaDia> listDiasAgendaDoctor) {
        this.listDiasAgendaDoctor = listDiasAgendaDoctor;
    }

    public AgendaDia getSelectedAgendaDia() {
        return selectedAgendaDia;
    }

    public void setSelectedAgendaDia(AgendaDia selectedAgendaDia) {
        this.selectedAgendaDia = selectedAgendaDia;
    }

    public Boolean getDisableStep2() {
        return disableStep2;
    }

    public void setDisableStep2(Boolean disableStep2) {
        this.disableStep2 = disableStep2;
    }

    public Boolean getDisableStep3() {
        return disableStep3;
    }

    public void setDisableStep3(Boolean disableStep3) {
        this.disableStep3 = disableStep3;
    }

    public Boolean getDisableStep4() {
        return disableStep4;
    }

    public void setDisableStep4(Boolean disableStep4) {
        this.disableStep4 = disableStep4;
    }

    public AgendaTurno getSelectedAgendaTurno() {
        return selectedAgendaTurno;
    }

    public void setSelectedAgendaTurno(AgendaTurno selectedAgendaTurno) {
        this.selectedAgendaTurno = selectedAgendaTurno;
    }

    public ArrayList<AgendaTurno> getListTurnosAgendaDoctor() {
        return listTurnosAgendaDoctor;
    }

    public void setListTurnosAgendaDoctor(ArrayList<AgendaTurno> listTurnosAgendaDoctor) {
        this.listTurnosAgendaDoctor = listTurnosAgendaDoctor;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Boolean getCancelarConsulta() {
        return cancelarConsulta;
    }

    public void setCancelarConsulta(Boolean cancelarConsulta) {
        this.cancelarConsulta = cancelarConsulta;
    }

    public ConsultaDoctor getSelectedConsulta() {
        return selectedConsulta;
    }

    public void setSelectedConsulta(ConsultaDoctor selectedConsulta) {
        this.selectedConsulta = selectedConsulta;
    }

    public SelectStringValue getConsultorioAgenda() {
        return consultorioAgenda;
    }

    public void setConsultorioAgenda(SelectStringValue consultorioAgenda) {
        this.consultorioAgenda = consultorioAgenda;
    }
    
    public void resetForm() {
        this.selectedAgendaDia = null;
        this.selectedAgendaTurno = null;
        this.selectedDoctor = null;
        this.selectedEspecialidad = null;
        this.selectedTipoConsulta = null;
        this.tipoPago = null;
        this.selectedConsulta = null;
        this.consultorioAgenda = null;
    }

}
