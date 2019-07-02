package com.uy.clinicasUY.bean.controller.handler;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContextHolder;

import com.uy.clinicasUY.bean.form.NewUserForm;
import com.uy.clinicasUY.bean.form.RecuperarPasswordForm;
import com.uy.clinicasUY.services.ComboUtilsServ;
import com.uy.clinicasUY.services.UsuarioServ;
import com.uy.clinicasUY.services.impl.ComboUtilsServImpl;
import com.uy.clinicasUY.services.impl.UsuarioServImpl;

@Component
public class RegistrarseHandler {
    
    ComboUtilsServ comboUtilsServ = new ComboUtilsServImpl();
    UsuarioServ usuarioServ = new UsuarioServImpl();
	
	public RegistrarseHandler() {
		
	}
	
	public void registroNewUser(Integer idUsuario) throws Exception {
	    //NewUserForm newUserForm = (NewUserForm) RequestContextHolder.getRequestContext().getFlowScope().get("newUserForm");
    }

    public void preRegistro() {
        NewUserForm newUserForm = (NewUserForm) RequestContextHolder.getRequestContext().getFlowScope().get("newUserForm");
        newUserForm.setDepartamentosList(comboUtilsServ.obtenerDepartamentos());
    }
    
    
    public String registrarNewUser() throws Exception {
        NewUserForm newUserForm = (NewUserForm) RequestContextHolder.getRequestContext().getFlowScope().get("newUserForm");
        //Aca primero verifica si ya existe el email en la base
       // if(usuarioServ.existeEmail(newUserForm.getNuevoUsuario().getUsuario().getUsername())){
            
        //    usuarioServ.registrarUsuario(newUserForm.getNuevoUsuario().getUsuario(), newUserForm.getNuevoUsuario().getPaciente());
            
            
       // }
        
        //Si no existe, entonces voy a registrarlo como nuevo usuario. 
        //el usuario se crea sin estar habilitado.
        //se envia un link con la activacion de la cuenta
        
       // resetForm();
        
        return "next";
    }
    
    public void recuperarPassword() {
        RecuperarPasswordForm recuperarPasswordForm = (RecuperarPasswordForm) RequestContextHolder.getRequestContext().getFlowScope().get("recuperarPasswordForm");
        recuperarPasswordForm.getEmail();
        //Primero verifica que el email exista, luego envia un email con el link a crear la nueva password.
    }
    
    public void postRegistro() {
        resetForm();
    }
    
    public void resetForm() {
        NewUserForm newUserForm = (NewUserForm) RequestContextHolder.getRequestContext().getFlowScope().get("newUserForm");
        newUserForm.setDepartamentosList(null);
        newUserForm.setNuevoUsuario(null);
        newUserForm.setSelectedDepartamento(null);
    }

    public void preRecuperar() {
       
    }

    public void postRecuperar() {
        RecuperarPasswordForm recuperarPasswordForm = (RecuperarPasswordForm) RequestContextHolder.getRequestContext().getFlowScope().get("recuperarPasswordForm");
        recuperarPasswordForm.setEmail(null);
    }

}
