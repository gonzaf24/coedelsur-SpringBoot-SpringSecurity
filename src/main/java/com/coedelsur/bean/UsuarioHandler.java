package com.uy.clinicasUY.bean.controller.handler;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContextHolder;

import com.uy.clinicasUY.bean.Usuario;
import com.uy.clinicasUY.bean.form.ClinicasTemplateForm;
import com.uy.clinicasUY.bean.form.PacienteForm;
import com.uy.clinicasUY.bean.form.UsuarioForm;
import com.uy.clinicasUY.services.UsuarioServ;
import com.uy.clinicasUY.services.impl.UsuarioServImpl;

@Component
public class UsuarioHandler {

    UsuarioServ usuaurioServ = new UsuarioServImpl();

    public UsuarioHandler() {
    }

    public void cambioPassword() throws Exception {
        UsuarioForm usuarioForm = (UsuarioForm) RequestContextHolder.getRequestContext().getFlowScope().get("usuarioForm");
        try {
            PacienteForm pacienteForm = (PacienteForm) RequestContextHolder.getRequestContext().getFlowScope().get("pacienteForm");
            usuaurioServ.cambiarPassword(usuarioForm.getUsuario().getPassword(), usuarioForm.getNewPassword(), pacienteForm.getPaciente().getId());
            Usuario usuario = usuaurioServ.obtenerUsuario(pacienteForm.getPaciente().getId());
            usuarioForm.setUsuario(usuario);
            usuarioForm.getUsuario().setPassword("");
            FacesContext.getCurrentInstance().addMessage("messagesPsw", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cambio realizado con �xito.", ""));
        } catch (Exception e) {
            usuarioForm.getUsuario().setPassword("");
            FacesContext.getCurrentInstance().addMessage("messagesPsw", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
        }
    }

    public void inicializarUsuario(Integer idUsuario) throws Exception {
        UsuarioForm usuarioForm = (UsuarioForm) RequestContextHolder.getRequestContext().getFlowScope().get("usuarioForm");
        usuarioForm.setUsuario(usuaurioServ.obtenerUsuario(idUsuario));
        usuaurioServ.registrarLogueo(usuarioForm.getUsuario().getUsername());
    }
    
    

    public void registrarLogueo() throws Exception {
        ClinicasTemplateForm clinicasTemplateForm = (ClinicasTemplateForm) RequestContextHolder.getRequestContext().getFlowScope().get("clinicasTemplateForm");
        UsuarioForm usuarioForm = (UsuarioForm) RequestContextHolder.getRequestContext().getFlowScope().get("usuarioForm");
        usuaurioServ.registrarLogueo(usuarioForm.getUsuario().getUsername());
        clinicasTemplateForm.setLogueosList(usuaurioServ.obtenerLogueos());

    }
    
    public void refrescarLista() throws Exception {
        ClinicasTemplateForm clinicasTemplateForm = (ClinicasTemplateForm) RequestContextHolder.getRequestContext().getFlowScope().get("clinicasTemplateForm");
        UsuarioForm usuarioForm = (UsuarioForm) RequestContextHolder.getRequestContext().getFlowScope().get("usuarioForm");
        clinicasTemplateForm.setLogueosList(usuaurioServ.obtenerLogueos());

    }
    
}
