package com.coedelsur.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.coedelsur.model.NewUser;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.UsuarioServ;

@Named
@ApplicationScoped
public class NewUserMB implements Serializable {
	
	@Inject
	ComboUtilsServ comboUtilsServ;
	
	@Inject
	UsuarioServ usuarioServ;

    private static final long serialVersionUID = 1L;
    private NewUser nuevoUsuario = new NewUser();
    private ArrayList<SelectStringValue> departamentosList = new ArrayList<SelectStringValue>();
    private SelectStringValue selectedDepartamento = new SelectStringValue();

    public NewUserMB() {
        super();
    }
    
    @PostConstruct
    public void init() {
    	departamentosList = comboUtilsServ.obtenerDepartamentos();
    }
    
    public void registrarNuevoPaciente() throws Exception{
    	if (!usuarioServ.existeEmail(nuevoUsuario.getUsuario().getUsername())) {
    		nuevoUsuario.getUsuario().setRole("ROLE_PACIENTE");
    		nuevoUsuario.getUsuario().setTipo("PACIENTE");
    		nuevoUsuario.getUsuario().setHabilitado(Boolean.TRUE);
    		nuevoUsuario.getPaciente().setEmail(nuevoUsuario.getUsuario().getUsername());
			usuarioServ.registrarUsuario(nuevoUsuario.getUsuario(), nuevoUsuario.getPaciente());
			nuevoUsuario = new NewUser();
		}else{
            FacesContext.getCurrentInstance().addMessage("messageRegister", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya registrado. Intente recuperar su contraseña", ""));
		}
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
