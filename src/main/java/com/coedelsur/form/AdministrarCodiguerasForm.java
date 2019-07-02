package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.model.DualListModel;

import com.coedelsur.model.SelectStringValue;

public class AdministrarCodiguerasForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<SelectStringValue> tipoConsultaList;
    private ArrayList<SelectStringValue> especialidadesList;
    private ArrayList<SelectStringValue> consultoriosList;
    private DualListModel<SelectStringValue> especialidades;
    private DualListModel<SelectStringValue> tipoConsultas;
    private DualListModel<SelectStringValue> consultorios;
    private String nombreARegistrar;

    public AdministrarCodiguerasForm() {
    }

    public ArrayList<SelectStringValue> getTipoConsultaList() {
        return tipoConsultaList;
    }

    public void setTipoConsultaList(ArrayList<SelectStringValue> tipoConsultaList) {
        this.tipoConsultaList = tipoConsultaList;
    }

    public ArrayList<SelectStringValue> getEspecialidadesList() {
        return especialidadesList;
    }

    public void setEspecialidadesList(ArrayList<SelectStringValue> especialidadesList) {
        this.especialidadesList = especialidadesList;
    }

    public DualListModel<SelectStringValue> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(DualListModel<SelectStringValue> especialidades) {
        this.especialidades = especialidades;
    }

    public DualListModel<SelectStringValue> getTipoConsultas() {
        return tipoConsultas;
    }

    public void setTipoConsultas(DualListModel<SelectStringValue> tipoConsultas) {
        this.tipoConsultas = tipoConsultas;
    }

    public ArrayList<SelectStringValue> getConsultoriosList() {
        return consultoriosList;
    }

    public void setConsultoriosList(ArrayList<SelectStringValue> consultoriosList) {
        this.consultoriosList = consultoriosList;
    }

    public DualListModel<SelectStringValue> getConsultorios() {
        return consultorios;
    }

    public void setConsultorios(DualListModel<SelectStringValue> consultorios) {
        this.consultorios = consultorios;
    }

    public String getNombreARegistrar() {
        return nombreARegistrar;
    }

    public void setNombreARegistrar(String nombreARegistrar) {
        this.nombreARegistrar = nombreARegistrar;
    }
}
