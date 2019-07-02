package com.coedelsur.form;

import java.io.Serializable;

import com.coedelsur.model.Paciente;

public class PacienteForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Paciente paciente;

    public PacienteForm() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
