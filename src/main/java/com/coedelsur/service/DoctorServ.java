package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.model.Usuario;

public interface DoctorServ {

    public Doctor obtenerDoctor(Integer id) throws Exception;
    
    public boolean crearUsuarioDoctor(Doctor doctor , Usuario usuario) throws Exception;
    
    public ArrayList<Doctor> obtenerListaDoctores() throws Exception;
    
    public ArrayList<SelectStringValue> obtenerListaDoctoresCodigos() throws Exception;

    public void editarDatosDoctor(Doctor doctorSelectedEdicion) throws Exception;
}
