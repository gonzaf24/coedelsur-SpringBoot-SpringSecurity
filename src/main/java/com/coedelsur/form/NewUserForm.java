package com.coedelsur.form;

import java.io.Serializable;
import java.util.ArrayList;

import com.coedelsur.model.NewUser;
import com.coedelsur.model.SelectStringValue;

public class NewUserForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private NewUser nuevoUsuario = new NewUser();
    private ArrayList<SelectStringValue> departamentosList = new ArrayList<SelectStringValue>();
    private SelectStringValue selectedDepartamento = new SelectStringValue();

    public NewUserForm() {
        super();
    }

    public NewUser getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(NewUser nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public ArrayList<SelectStringValue> getDepartamentosList() {
        return departamentosList;
    }

    public void setDepartamentosList(ArrayList<SelectStringValue> departamentosList) {
        this.departamentosList = departamentosList;
    }

    public SelectStringValue getSelectedDepartamento() {
        return selectedDepartamento;
    }

    public void setSelectedDepartamento(SelectStringValue selectedDepartamento) {
        this.selectedDepartamento = selectedDepartamento;
    }
}
