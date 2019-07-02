package com.coedelsur.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.ConsultorioPersistence;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ConsultorioServ;

@Component
public class ConsultorioServImpl implements ConsultorioServ {

    @Override
    public List<SelectStringValue> obtenerConsultorios() throws Exception {
        try {
            return ConsultorioPersistence.obtenerConsultorios();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }
}
