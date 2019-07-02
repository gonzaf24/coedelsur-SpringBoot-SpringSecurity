package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.Archivo;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;

public interface PacienteServ {

    public Paciente obtenerPaciente(Integer id) throws Exception;

    public boolean registrarPaciente(Paciente paciente) throws Exception;

    public ArrayList<PacienteFile> obtenerArchivosPaciente(Integer id) throws Exception;

    public Archivo obtenerArchivo(Integer idArchivo) throws Exception;

    public ArrayList<Paciente> obtenerPacientesFiltro(String nombre, String apellidos, Integer ci) throws Exception;

    public Boolean nuevoArchivoPaciente(PacienteFile pacienteFile) throws Exception;

    public Boolean eliminarDocumento(PacienteFile pacienteFile) throws Exception;

    public boolean editarDatosPaciente(Paciente paciente) throws Exception;
}
