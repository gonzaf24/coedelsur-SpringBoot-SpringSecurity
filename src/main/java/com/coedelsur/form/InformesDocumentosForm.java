package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import org.primefaces.model.StreamedContent;

import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;

public class InformesDocumentosForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<PacienteFile> listMisFiles;
    private PacienteFile selectedPacienteFile;
    private transient StreamedContent archivo;
    private Paciente paciente;

    public InformesDocumentosForm() {
    }

    public ArrayList<PacienteFile> getListMisFiles() {
        return listMisFiles;
    }

    public void setListMisFiles(ArrayList<PacienteFile> listMisFiles) {
        this.listMisFiles = listMisFiles;
    }

    public PacienteFile getSelectedPacienteFile() {
        return selectedPacienteFile;
    }

    public void setSelectedPacienteFile(PacienteFile selectedPacienteFile) {
        this.selectedPacienteFile = selectedPacienteFile;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }
}
