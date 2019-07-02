package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.SelectStringValue;

public class AdministrarConsultasForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<ConsultaDoctor> adminConsultasList = new ArrayList<ConsultaDoctor>();
    private SelectStringValue selectedEspecialidad = new SelectStringValue();
    private SelectStringValue selectedTipoConsulta = new SelectStringValue();
    private Integer precio;
    private String descripcion;

    public AdministrarConsultasForm() {
    }

    public ArrayList<ConsultaDoctor> getAdminConsultasList() {
        return adminConsultasList;
    }

    public void setAdminConsultasList(ArrayList<ConsultaDoctor> adminConsultasList) {
        this.adminConsultasList = adminConsultasList;
    }

    public SelectStringValue getSelectedEspecialidad() {
        return selectedEspecialidad;
    }

    public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
        this.selectedEspecialidad = selectedEspecialidad;
    }

    public SelectStringValue getSelectedTipoConsulta() {
        return selectedTipoConsulta;
    }

    public void setSelectedTipoConsulta(SelectStringValue selectedTipoConsulta) {
        this.selectedTipoConsulta = selectedTipoConsulta;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
