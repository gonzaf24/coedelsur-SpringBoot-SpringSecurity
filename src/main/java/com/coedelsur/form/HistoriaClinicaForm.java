package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.model.StreamedContent;

import com.coedelsur.model.Antecedente;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;

public class HistoriaClinicaForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Paciente paciente;
    private Integer selectedIdPaciente;
    private ConsultaMedica nuevaConsultaMedica = new ConsultaMedica();
    private Antecedente nuevoAntecedente = new Antecedente();
    private ArrayList<ConsultaMedica> consultasMedicasList = new ArrayList<ConsultaMedica>();
    private ArrayList<Antecedente> antecedentesList = new ArrayList<Antecedente>();
    private Antecedente selectedAntecedente = new Antecedente();
    private ConsultaMedica selectedConsultaMedica = new ConsultaMedica();
    private ArrayList<PacienteFile> listMisFiles;
    private transient StreamedContent archivo;
    private String nuevoDocumento;
    private String nombreArchivoFotoDoc;
    private PacienteFile pacienteFile = new PacienteFile();

    public HistoriaClinicaForm() {
    }
    public PacienteFile getPacienteFile() {
        return pacienteFile;
    }

    public void setPacienteFile(PacienteFile pacienteFile) {
        this.pacienteFile = pacienteFile;
    }

    public String getNombreArchivoFotoDoc() {
        return nombreArchivoFotoDoc;
    }

    public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
        this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
    }

    public String getNuevoDocumento() {
        return nuevoDocumento;
    }

    public void setNuevoDocumento(String nuevoDocumento) {
        this.nuevoDocumento = nuevoDocumento;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

    public ArrayList<PacienteFile> getListMisFiles() {
        return listMisFiles;
    }

    public void setListMisFiles(ArrayList<PacienteFile> listMisFiles) {
        this.listMisFiles = listMisFiles;
    }

    public ConsultaMedica getSelectedConsultaMedica() {
        return selectedConsultaMedica;
    }

    public void setSelectedConsultaMedica(ConsultaMedica selectedConsultaMedica) {
        this.selectedConsultaMedica = selectedConsultaMedica;
    }

    public Antecedente getSelectedAntecedente() {
        return selectedAntecedente;
    }

    public void setSelectedAntecedente(Antecedente selectedAntecedente) {
        this.selectedAntecedente = selectedAntecedente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getSelectedIdPaciente() {
        return selectedIdPaciente;
    }

    public void setSelectedIdPaciente(Integer selectedIdPaciente) {
        this.selectedIdPaciente = selectedIdPaciente;
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

    public ArrayList<ConsultaMedica> getConsultasMedicasList() {
        return consultasMedicasList;
    }

    public void setConsultasMedicasList(ArrayList<ConsultaMedica> consultasMedicasList) {
        this.consultasMedicasList = consultasMedicasList;
    }

    public ArrayList<Antecedente> getAntecedentesList() {
        return antecedentesList;
    }

    public void setAntecedentesList(ArrayList<Antecedente> antecedentesList) {
        this.antecedentesList = antecedentesList;
    }
}
