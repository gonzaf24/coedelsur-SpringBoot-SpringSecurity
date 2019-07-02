package com.coedelsur.bean.paciente;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.Archivo;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.service.PacienteServ;
import com.coedelsur.util.Base64;

@SessionScope
@Component
public class InformesDocumentosMB implements Serializable {

	@Inject
    PacienteServ pacienteServ;
	@Inject
    SessionMB sessionMB;
	   
    private static final long serialVersionUID = 1L;
    private ArrayList<PacienteFile> listMisFiles;
    private PacienteFile selectedPacienteFile;
    private transient StreamedContent archivo;
    private Paciente paciente;

    public InformesDocumentosMB() {
    }

    public void init() throws Exception {
        setListMisFiles(new ArrayList<PacienteFile>(pacienteServ.obtenerArchivosPaciente(sessionMB.getPaciente().getId())));
        setPaciente(sessionMB.getPaciente());
    }

    public void obtenerArchivo(Integer idArchivo) throws Exception {
        Archivo archivo = pacienteServ.obtenerArchivo(idArchivo);
        byte[] base64DecodedByteArray = Base64.decode(archivo.getDocumento());
        InputStream is = new ByteArrayInputStream(base64DecodedByteArray);
        setArchivo(new DefaultStreamedContent(is, "image/png", "documentosClinico.png"));
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
