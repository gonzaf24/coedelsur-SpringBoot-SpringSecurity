package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.PacientePersistence;
import com.coedelsur.model.Archivo;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.service.PacienteServ;

@Component
public class PacienteServImpl implements PacienteServ {

    @Override
    public Paciente obtenerPaciente(Integer id) throws Exception {
        try {
            return PacientePersistence.obtenerPaciente(id);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obetener el paciente.");
        }
    }

    @Override
    public boolean registrarPaciente(Paciente paciente) throws Exception {
        try {
            return PacientePersistence.registrarPaciente(paciente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al registrar el paciente.");
        }
    }

    @Override
    public ArrayList<PacienteFile> obtenerArchivosPaciente(Integer idPaciente) throws Exception {
        try {
            return PacientePersistence.obtenerArchivosPaciente(idPaciente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }
    
    @Override
    public Archivo obtenerArchivo(Integer idArchivo) throws Exception {
        try {
            return PacientePersistence.obtenerArchivo(idArchivo);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Paciente> obtenerPacientesFiltro(String nombre, String apellidos, Integer ci) throws Exception {
        try {
            return PacientePersistence.obtenerPacientesFiltro( nombre,  apellidos,  ci);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los pacientes por filtro. " + e.getMessage());
        }
    }
    
    @Override
    public Boolean nuevoArchivoPaciente(PacienteFile pacienteFile) throws Exception {
        try {
            return PacientePersistence.nuevoArchivoPaciente(pacienteFile);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener los documentos del paciente. " + e.getMessage());
        }
    }

    @Override
    public Boolean eliminarDocumento(PacienteFile pacienteFile) throws Exception  {
        try {
            return PacientePersistence.eliminarDocumento(pacienteFile);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar el documento. " + e.getMessage());
        }
    }

    @Override
    public boolean editarDatosPaciente(Paciente paciente) throws Exception  {
        
        try {
            return PacientePersistence.editarPaciente(paciente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar el documento. " + e.getMessage());
        }
    }
    
    
}
