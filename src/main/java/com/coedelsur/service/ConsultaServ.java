package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.ConsultaDoctor;

public interface ConsultaServ {

    public ArrayList<ConsultaDoctor> obtenerConsultasHabilitados() throws Exception;

    public ArrayList<ConsultaDoctor> obtenerConsultas() throws Exception;

    public void crearConsulta(ConsultaDoctor consDoc) throws Exception;
    
}
