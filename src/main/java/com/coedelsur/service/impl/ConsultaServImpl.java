package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.ConsultaPersistence;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.service.ConsultaServ;

@Component
public class ConsultaServImpl implements ConsultaServ {

    @Override
    public ArrayList<ConsultaDoctor> obtenerConsultasHabilitados() throws Exception {
        try {
            return ConsultaPersistence.obtenerConsultasHabilitados();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<ConsultaDoctor> obtenerConsultas() throws Exception {
        try {
            return ConsultaPersistence.obtenerConsultas();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }

    @Override
    public void crearConsulta(ConsultaDoctor consDoc) throws Exception {
        try {
            ConsultaPersistence.crearConsulta(consDoc);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear la consulta. " + e.getMessage());
        }
        
    }
}
