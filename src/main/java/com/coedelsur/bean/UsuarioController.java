package com.uy.clinicasUY.bean.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uy.clinicasUY.bean.controller.handler.RegistrarseHandler;

@Controller
public class UsuarioController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(value = "/registarUsuario", method = RequestMethod.POST)
    public void registro(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/app/registro");
    }
    
    @RequestMapping(value = "/recuperarContrasena", method = RequestMethod.POST)
    public void recuperarContrasena(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectStrategy.sendRedirect(request, response, "/app/recuperar");
    }
    
    public void preRegistrarse() throws Exception {
        registrarseHandler.preRegistro();
    }
    
    public void postRegistrarse() throws Exception {
        registrarseHandler.postRegistro();
    }
    
    public void preRecuperar() throws Exception {
        registrarseHandler.preRecuperar();
    }
    
    public void postRecuperar() throws Exception {
        registrarseHandler.postRecuperar();
    }
    
    public String registerAction() throws Exception {
       return registrarseHandler.registrarNewUser();
    }
    
    
    
    @Inject
    private RegistrarseHandler registrarseHandler;
    
}