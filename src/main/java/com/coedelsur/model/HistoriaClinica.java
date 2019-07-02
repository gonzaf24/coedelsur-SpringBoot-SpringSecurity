package com.coedelsur.model;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Antecedente> antecedentes;
    private ArrayList<ConsultaMedica> consultas;
    private ArrayList<ProcedimientoDiagnosticoTerapeutico> procedimientosDiagnosticosTerapeuticos;
    private ArrayList<PacienteFile> documentos;

    public HistoriaClinica() {
    }

    public ArrayList<Antecedente> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(ArrayList<Antecedente> antecedentes) {
        this.antecedentes = antecedentes;
    }

    public ArrayList<ConsultaMedica> getConsultas() {
        return consultas;
    }

    public void setConsultas(ArrayList<ConsultaMedica> consultas) {
        this.consultas = consultas;
    }

    public ArrayList<ProcedimientoDiagnosticoTerapeutico> getProcedimientosDiagnosticosTerapeuticos() {
        return procedimientosDiagnosticosTerapeuticos;
    }

    public void setProcedimientosDiagnosticosTerapeuticos(ArrayList<ProcedimientoDiagnosticoTerapeutico> procedimientosDiagnosticosTerapeuticos) {
        this.procedimientosDiagnosticosTerapeuticos = procedimientosDiagnosticosTerapeuticos;
    }

    public ArrayList<PacienteFile> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(ArrayList<PacienteFile> documentos) {
        this.documentos = documentos;
    }
}
