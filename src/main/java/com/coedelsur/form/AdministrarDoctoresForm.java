package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringString;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.model.Usuario;

public class AdministrarDoctoresForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Doctor> adminDoctoresList = new ArrayList<Doctor>();
    private ArrayList<SelectStringString> sexoList = new ArrayList<SelectStringString>(Arrays.asList(new SelectStringString("Dr.", "H"), new SelectStringString("Dra.", "M")));
    private Doctor doctorSelectedEdicion = new Doctor();
    private Doctor nuevoDoc = new Doctor();
    private Usuario usuario = new Usuario();
    private SelectStringValue selectedDepartamento = new SelectStringValue();
    private SelectStringValue selectedEspecialidad = new SelectStringValue();
    private SelectStringString selectedSexo = new SelectStringString();
    private String nombreArchivoFotoDoc;
    private String pass1;
    private String pass2;

    public AdministrarDoctoresForm() {
    }

    public ArrayList<Doctor> getAdminDoctoresList() {
        return adminDoctoresList;
    }

    public void setAdminDoctoresList(ArrayList<Doctor> adminDoctoresList) {
        this.adminDoctoresList = adminDoctoresList;
    }

    public Doctor getDoctorSelectedEdicion() {
        return doctorSelectedEdicion;
    }

    public void setDoctorSelectedEdicion(Doctor doctorSelectedEdicion) {
        this.doctorSelectedEdicion = doctorSelectedEdicion;
    }

    public String getNombreArchivoFotoDoc() {
        return nombreArchivoFotoDoc;
    }

    public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
        this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
    }

    public Doctor getNuevoDoc() {
        return nuevoDoc;
    }

    public void setNuevoDoc(Doctor nuevoDoc) {
        this.nuevoDoc = nuevoDoc;
    }

    public SelectStringValue getSelectedDepartamento() {
        return selectedDepartamento;
    }

    public void setSelectedDepartamento(SelectStringValue selectedDepartamento) {
        this.selectedDepartamento = selectedDepartamento;
    }

    public SelectStringValue getSelectedEspecialidad() {
        return selectedEspecialidad;
    }

    public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
        this.selectedEspecialidad = selectedEspecialidad;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SelectStringString getSelectedSexo() {
        return selectedSexo;
    }

    public void setSelectedSexo(SelectStringString selectedSexo) {
        this.selectedSexo = selectedSexo;
    }

    public ArrayList<SelectStringString> getSexoList() {
        return sexoList;
    }

    public void setSexoList(ArrayList<SelectStringString> sexoList) {
        this.sexoList = sexoList;
    }
}
