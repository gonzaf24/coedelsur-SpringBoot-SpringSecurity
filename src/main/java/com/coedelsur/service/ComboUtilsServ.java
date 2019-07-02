package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringValue;

public interface ComboUtilsServ {

    public ArrayList<SelectStringValue> obtenerDepartamentos();

    public ArrayList<SelectStringValue> obtenerTipoConsultas();

    public ArrayList<SelectStringValue> obtenerEspecialdades();
    
    public ArrayList<SelectStringValue> obtenerTipoConsultasTodas();

    public ArrayList<SelectStringValue> obtenerEspecialdadesTodas();

    public ArrayList<Doctor> obtenerDoctores(Integer selectedEspecialidad);

    public ArrayList<SelectStringValue> obtenerEspecialidadesPorTipoConsultaSolicitarCita(Integer id);
    
    public ArrayList<SelectStringValue> obtenerTipoConsultasSolicitarCita() throws Exception;

    public ConsultaDoctor obtenerConsultaSolicitarCita(Integer codTipoConsulta, Integer codEspecialidad) throws Exception;

    public ArrayList<SelectStringValue> obtenerConsultoriosTodos();
    
    public boolean crearCodigoTipoConsulta (String descripcion) throws Exception ;
    
    public boolean crearCodigoEspecialidad (String descripcion) throws Exception ;
    
    public boolean crearCodigoConsultorio (String descripcion) throws Exception ;

    

}
