package com.coedelsur.form;

import java.io.Serializable;

import com.coedelsur.model.Usuario;

public class UsuarioForm extends ClinicasTemplateForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private Usuario usuario;
    private String newPassword;

    public UsuarioForm() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
