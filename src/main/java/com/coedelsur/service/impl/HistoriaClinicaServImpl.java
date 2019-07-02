package com.coedelsur.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.HistoriaClinicaPersistence;
import com.coedelsur.model.Antecedente;
import com.coedelsur.model.AntecedenteObject;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;
import com.coedelsur.service.HistoriaClinicaServ;

@Component
public class HistoriaClinicaServImpl implements HistoriaClinicaServ {

    @Override
    public ArrayList<HistoriaClinica> obtenerHistoriaClinica(Paciente paciente) throws Exception {
        try {
            return HistoriaClinicaPersistence.obtenerHistoriaClinica(paciente.getId());
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener h.clinica. " + e.getMessage());
        }
    }

    @Override
    public boolean crearHistoriaClinica(Paciente paciente) throws Exception {
        try {
            return HistoriaClinicaPersistence.crearHistoriaClinica(paciente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear h.clinica. " + e.getMessage());
        }
    }
    
    @Override
    public boolean nuevaConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
        try {
            return HistoriaClinicaPersistence.nuevaConsultaMedica(consultaMedica);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear consulta medica. " + e.getMessage());
        }
    }
    
    @Override
    public boolean editarConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
        try {
            HistoriaClinicaPersistence.editarDiagnosticoDeConsultaMedica(consultaMedica);
            return HistoriaClinicaPersistence.editarConsultaMedica(consultaMedica);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al editar consulta medica. " + e.getMessage());
        }
    }
    
    @Override
    public boolean eliminarConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
        try {
            return HistoriaClinicaPersistence.eliminarConsultaMedica(consultaMedica);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar consulta medica. " + e.getMessage());
        }
    }
    
    @Override
    public boolean nuevoAntecedente(Antecedente antecedente) throws Exception {
        try {
            return HistoriaClinicaPersistence.nuevoAntecedente(antecedente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear antecedente. " + e.getMessage());
        }
    }
    
    @Override
    public boolean nuevoAntecedentePersonal(Antecedente antecedente) throws Exception {
        try {
            return HistoriaClinicaPersistence.nuevoAntecedentePersonal(antecedente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear antecedente personal. " + e.getMessage());
        }
    }
    
    
    @Override
    public boolean nuevoAntecedenteFamiliar(Antecedente antecedente) throws Exception {
        try {
            return HistoriaClinicaPersistence.nuevoAntecedenteFamiliar(antecedente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear antecedente familiar. " + e.getMessage());
        }
    }
    
    
    @Override
    public boolean nuevoAntecedenteMedicamento(Antecedente antecedente) throws Exception {
        try {
            return HistoriaClinicaPersistence.nuevoAntecedenteMedicamento(antecedente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al crear antecedente medicamento. " + e.getMessage());
        }
    }
      
    @Override
    public boolean editarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception {
        try {
            return HistoriaClinicaPersistence.editarAntecedentePersonal(antecedentePersonal);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al editar antecedente personal. " + e.getMessage());
        }
    }
       
    @Override
    public boolean editarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception {
        try {
            return HistoriaClinicaPersistence.editarAntecedenteFamiliar(antecedenteFamiliar);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al editar antecedente familiar. " + e.getMessage());
        }
    }
    
    @Override
    public boolean editarAntecedenteMedicamento(Medicamento antecedenteMedicamento) throws Exception {
        try {
            return HistoriaClinicaPersistence.editarAntecedenteMedicamento(antecedenteMedicamento);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al editar antecedente medicamento. " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<Antecedente> obtenerAntecedentesList(Integer id) throws Exception {
        try {
            return HistoriaClinicaPersistence.obtenerAntecedentesList(id);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener antecedentes list " + e.getMessage());
        }
    }
    
    @Override
    public ArrayList<ConsultaMedica> obtenerConsultasMedicasList(Integer id) throws Exception {
        try {
            return HistoriaClinicaPersistence.obtenerConsultasMedicasList(id);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener consultas medicas list. " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarAntecedentePersonal(AntecedenteObject antecedentePersonal) throws Exception {
        try {
            return HistoriaClinicaPersistence.eliminarAntecedentePersonal(antecedentePersonal);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar antecedente personal. " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarAntecedenteFamiliar(AntecedenteObject antecedenteFamiliar) throws Exception {
        try {
            return HistoriaClinicaPersistence.eliminarAntecedenteFamiliar(antecedenteFamiliar);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar antecedente familiar. " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarAntecedenteMedicamento(Medicamento antecedenteMedicamento) throws Exception {
        try {
            return HistoriaClinicaPersistence.eliminarAntecedenteMedicamento(antecedenteMedicamento);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar antecedente medicamento. " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarAntecedente(Antecedente antecedente) throws Exception {
        try {
            if(antecedente.getPersonal()!=null && antecedente.getPersonal().getId()!=null){
                HistoriaClinicaPersistence.eliminarAntecedentePersonal(antecedente.getPersonal());
            }
            if(antecedente.getFamiliar()!=null && antecedente.getFamiliar().getId()!=null){
                HistoriaClinicaPersistence.eliminarAntecedenteFamiliar(antecedente.getFamiliar());
            }
            if(antecedente.getMedicamento()!=null && antecedente.getMedicamento().getId()!=null){
                HistoriaClinicaPersistence.eliminarAntecedenteMedicamento(antecedente.getMedicamento());
            }
            return HistoriaClinicaPersistence.eliminarAntecedente(antecedente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al eliminar antecedente. " + e.getMessage());
        }
    }
      
}
