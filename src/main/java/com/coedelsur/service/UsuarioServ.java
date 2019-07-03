package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.Paciente;
import com.coedelsur.model.Usuario;

public interface UsuarioServ {

    public void cambiarPassword(String oldPsw, String newPsw, Integer id) throws Exception;

    public String obtenerPassword(Integer id) throws Exception;

    public Usuario obtenerUsuario(Integer id) throws Exception;

    public boolean registrarUsuario(Usuario user, Paciente paciente) throws Exception;
    
    public boolean existeEmail(String email) throws Exception;

    public void registrarLogueo(String string) throws Exception;

    public ArrayList<String> obtenerLogueos() throws Exception;
}
