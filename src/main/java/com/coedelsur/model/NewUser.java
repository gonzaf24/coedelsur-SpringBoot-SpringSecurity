package com.coedelsur.model;

import java.io.Serializable;

public class NewUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Paciente paciente = new Paciente();
    private Usuario usuario = new Usuario();

    public NewUser() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
