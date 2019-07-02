package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.PacientePersistence;
import com.coedelsur.database.persistence.UsuarioPersistence;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.Usuario;
import com.coedelsur.service.UsuarioServ;

@Component
public class UsuarioServImpl implements UsuarioServ{


	@Override
	public void cambiarPassword(String oldPsw, String newPsw, Integer id) throws Exception {
		try{
			String oldPassword = obtenerPassword(id);
			if(oldPsw.equals(oldPassword)){
				UsuarioPersistence.cambiarPassword(newPsw, id);
			}else{
				throw new Exception("Contraseña actual incorrecta.");
			}
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public String obtenerPassword(Integer id) throws Exception {
		String salida = new String();
		try{
			salida = UsuarioPersistence.obtenerPassword(id);
		}catch(Exception e){
			throw e;
		}
		return salida;
	}
	
	public Usuario obtenerUsuario(Integer id) throws Exception{
		Usuario salida = new Usuario();
		try{
			salida = UsuarioPersistence.obtenerUsuario(id);
		}catch(Exception e){
			throw e;
		}
		return salida;
	}
	
	public boolean registrarUsuario(Usuario usuario, Paciente paciente) throws Exception{
	    boolean registrado = false;
        try{
           long id = UsuarioPersistence.registrarUsuario(usuario);
           paciente.setId(new Long(id).intValue());   
           registrado = PacientePersistence.registrarPaciente(paciente);
        }catch(Exception e){
            throw e;
        }
        return registrado;
    }

    @Override
    public boolean existeEmail(String email) throws Exception {
        try{
            return UsuarioPersistence.existeEmail(email);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public void registrarLogueo(String string) throws Exception {
        try{
            UsuarioPersistence.registrarLogueo(string);
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public ArrayList<String> obtenerLogueos() throws Exception {
        try{
            return UsuarioPersistence.obtenerRegistroLogueo();
        }catch(Exception e){
            throw e;
        }
    }
	

}
