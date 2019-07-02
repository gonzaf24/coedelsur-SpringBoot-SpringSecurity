package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.CodiguerasPersistence;
import com.coedelsur.database.persistence.ConsultaPersistence;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ComboUtilsServ;

@Component
public class ComboUtilsServImpl implements ComboUtilsServ {

    @Override
    public ArrayList<SelectStringValue> obtenerDepartamentos() {
        return CodiguerasPersistence.obtenerCodigueraDepartamentosList();
    }

    public ArrayList<SelectStringValue> obtenerTipoConsultas() {
        return CodiguerasPersistence.obtenerTipoConsultas();
    }

    public ArrayList<SelectStringValue> obtenerEspecialdades() {
        return CodiguerasPersistence.obtenerEspecialidades();
    }
    
    public ArrayList<SelectStringValue> obtenerTipoConsultasTodas() {
        return CodiguerasPersistence.obtenerTipoConsultasTodas();
    }

    public ArrayList<SelectStringValue> obtenerEspecialdadesTodas() {
        return CodiguerasPersistence.obtenerEspecialidadesTodas();
    }

    @Override
    public ArrayList<Doctor> obtenerDoctores(Integer selectedEspecialidad) {
        return CodiguerasPersistence.obtenerDoctoresPorEspecialidad(selectedEspecialidad);
    }

    @Override
    public ArrayList<SelectStringValue> obtenerEspecialidadesPorTipoConsultaSolicitarCita(Integer id) {
        return CodiguerasPersistence.obtenerEspecialidadesPorTipoConsultaSolicitarCita(id);
    }

    @Override
    public ArrayList<SelectStringValue> obtenerConsultoriosTodos() {
        return CodiguerasPersistence.obtenerConsultoriosTodos();
    }

    @Override
    public boolean crearCodigoTipoConsulta(String descripcion) throws Exception {
        try {
           return ConsultaPersistence.crearCodigoTipoConsulta(descripcion);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear codigo tipo consulta. " + e.getMessage());
        }
    }

    @Override
    public boolean crearCodigoEspecialidad(String descripcion) throws Exception {
        try {
            return ConsultaPersistence.crearCodigoEspecialidad(descripcion);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear codigo especialidad. " + e.getMessage());
        }
    }

    @Override
    public boolean crearCodigoConsultorio(String descripcion) throws Exception {
        try {
            return ConsultaPersistence.crearCodigoConsultorio(descripcion);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear codigo consultorio. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<SelectStringValue> obtenerTipoConsultasSolicitarCita() throws Exception {
        try {
            return CodiguerasPersistence.obtenerTipoConsultasSolicitarCita();
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear codigo consultorio. " + e.getMessage());
        }
    }

    @Override
    public ConsultaDoctor obtenerConsultaSolicitarCita(Integer codTipoConsulta, Integer codEspecialidad) throws Exception {
        try {
            return CodiguerasPersistence.obtenerConsultaSolicitarCita(codTipoConsulta,codEspecialidad);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear codigo consultorio. " + e.getMessage());
        }
    }

}
