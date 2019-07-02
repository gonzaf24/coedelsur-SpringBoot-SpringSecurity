package com.coedelsur.service;

import java.util.ArrayList;

import com.coedelsur.model.Antecedente;
import com.coedelsur.model.AntecedenteObject;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;

public interface HistoriaClinicaServ {

    public ArrayList<HistoriaClinica> obtenerHistoriaClinica(Paciente paciente) throws Exception ;

    public boolean crearHistoriaClinica(Paciente paciente) throws Exception ;

    public boolean nuevaConsultaMedica(ConsultaMedica consultaMedica) throws Exception;

    public boolean editarConsultaMedica(ConsultaMedica consultaMedica) throws Exception;

    public boolean eliminarConsultaMedica(ConsultaMedica consultaMedica) throws Exception;

    public boolean nuevoAntecedente(Antecedente antecedente) throws Exception;
    
    public boolean editarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception;

    public boolean editarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception;

    public boolean editarAntecedenteMedicamento(Medicamento antecedenteMedicamento) throws Exception;

    public ArrayList<Antecedente> obtenerAntecedentesList(Integer id) throws Exception;

    public ArrayList<ConsultaMedica> obtenerConsultasMedicasList(Integer id) throws Exception;
    
    public boolean eliminarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception;

    public boolean eliminarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception;

    public boolean eliminarAntecedenteMedicamento(Medicamento antecedenteMedicamento) throws Exception;
    
    public boolean eliminarAntecedente(Antecedente Antecedente) throws Exception;

    public boolean nuevoAntecedenteFamiliar(Antecedente antecedente) throws Exception;

    public boolean nuevoAntecedentePersonal(Antecedente antecedente) throws Exception;

    public boolean nuevoAntecedenteMedicamento(Antecedente antecedente) throws Exception;
    
}
