package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.model.StreamedContent;

import com.coedelsur.model.Antecedente;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.model.SelectStringValue;

public class FichaPacienteForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Paciente paciente;
    private String email;
    private String foto;
    private String nombreArchivoFotoDoc;
    private SelectStringValue selectedDepartamento = new SelectStringValue();
    private HistoriaClinica historiaClinica;
    private ArrayList<HistoriaClinica> historiaClinicaList = new ArrayList<HistoriaClinica>();
    private Integer selectedIdPaciente;
    private ArrayList<PacienteFile> listMisFiles;
    private transient StreamedContent archivo;
    private String nuevoDocumento;
    private ConsultaMedica nuevaConsultaMedica = new ConsultaMedica();
    private Antecedente nuevoAntecedente = new Antecedente();
    private PacienteFile pacienteFile = new PacienteFile();

    public FichaPacienteForm() {
    }

    public PacienteFile getPacienteFile() {
        return pacienteFile;
    }

    public void setPacienteFile(PacienteFile pacienteFile) {
        this.pacienteFile = pacienteFile;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombreArchivoFotoDoc() {
        return nombreArchivoFotoDoc;
    }

    public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
        this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
    }

    public SelectStringValue getSelectedDepartamento() {
        return selectedDepartamento;
    }

    public void setSelectedDepartamento(SelectStringValue selectedDepartamento) {
        this.selectedDepartamento = selectedDepartamento;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public ArrayList<HistoriaClinica> getHistoriaClinicaList() {
        return historiaClinicaList;
    }

    public void setHistoriaClinicaList(ArrayList<HistoriaClinica> historiaClinicaList) {
        this.historiaClinicaList = historiaClinicaList;
    }

    public Integer getSelectedIdPaciente() {
        return selectedIdPaciente;
    }

    public void setSelectedIdPaciente(Integer selectedIdPaciente) {
        this.selectedIdPaciente = selectedIdPaciente;
    }

    public ArrayList<PacienteFile> getListMisFiles() {
        return listMisFiles;
    }

    public void setListMisFiles(ArrayList<PacienteFile> listMisFiles) {
        this.listMisFiles = listMisFiles;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

    public String getNuevoDocumento() {
        return nuevoDocumento;
    }

    public void setNuevoDocumento(String nuevoDocumento) {
        this.nuevoDocumento = nuevoDocumento;
    }

    public ConsultaMedica getNuevaConsultaMedica() {
        return nuevaConsultaMedica;
    }

    public void setNuevaConsultaMedica(ConsultaMedica nuevaConsultaMedica) {
        this.nuevaConsultaMedica = nuevaConsultaMedica;
    }

    public Antecedente getNuevoAntecedente() {
        return nuevoAntecedente;
    }

    public void setNuevoAntecedente(Antecedente nuevoAntecedente) {
        this.nuevoAntecedente = nuevoAntecedente;
    }
}
