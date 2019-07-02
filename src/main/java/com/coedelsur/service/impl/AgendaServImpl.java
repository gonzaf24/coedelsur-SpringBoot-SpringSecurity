package com.coedelsur.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.coedelsur.database.persistence.AgendaPersistence;
import com.coedelsur.model.Agenda;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.Consulta;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.AgendaServ;

@Component
public class AgendaServImpl implements AgendaServ {

    @Override
    public List<AgendaDia> obtenerDiasStep2(Doctor doc) throws Exception {
        try {
            return AgendaPersistence.obtenerDiasAgendaDoctor(doc);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al consultar la agenda. " + e.getMessage());
        }
    }

    @Override
    public List<AgendaTurno> obtenerTurnosStep3(Doctor doc, AgendaDia agDia) throws Exception {
        try {
            return AgendaPersistence.obtenerTurnosAgendaDoctor(doc, agDia);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al consultar la agenda.  " + e.getMessage());
        }
    }

    @Override
    public boolean reservaTurno(AgendaTurno agTurno, Paciente paciente,ConsultaDoctor consultaDoctor) throws Exception {
        try {
            return AgendaPersistence.reservarTurno(agTurno, paciente, consultaDoctor);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al registar el turno. " + e.getMessage());
        }
    }

    @Override
    public boolean cancelarReservaTurno(AgendaTurno selectedAgendaTurno) throws Exception {
        try {
            return AgendaPersistence.cancelarReservaTurno(selectedAgendaTurno);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al cancelar el turno. " + e.getMessage());
        }
    }

    @Override
    public boolean turnoDisponible(AgendaTurno selectedAgendaTurno) throws Exception {
        try {
            return AgendaPersistence.turnoDisponible(selectedAgendaTurno);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener el. " + e.getMessage());
        }
    }

    @Override
    public List<Consulta> obtenerConsultasPaciente(Integer idPaciente) throws Exception {
        try {
            return AgendaPersistence.obtenerConsultasPaciente(idPaciente);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener las consultas del paciente. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Agenda> obtenerAgendaPorDias(ArrayList<Date> arrayList) throws Exception {
        try {
            return AgendaPersistence.obtenerAgendaPorDias(arrayList);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la agenda. " + e.getMessage());
        }
    }

    @Override
    public boolean confirmarPersistirTurnos(ArrayList<Agenda> listaAgendaGenerada) throws Exception {
        try {
            return AgendaPersistence.confirmarPersistirTurnos(listaAgendaGenerada);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la agenda. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Agenda> obtenerAgendaDoctor(Integer idDoctor) throws Exception {
        try {
            ArrayList<Agenda> salida = AgendaPersistence.obtenerAgendaDoctor(idDoctor);
            for (int i = 0; i < salida.size(); i++) {
                Agenda aux = salida.get(i);
                if(aux.getIdPaciente()!=null && aux.getIdConsulta() != null){
                    Agenda  agendaAux = AgendaPersistence.obtenerAgendaDoctorPaciente(aux);
                    aux.setEspecialidad(agendaAux.getEspecialidad());
                    aux.setTipoConsulta(agendaAux.getTipoConsulta());
                }
            }
            return salida;
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la agenda del doctor. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Agenda> obtenerAgendaDoctorHistorico(Integer idDoctor) throws Exception {
        try {
            
            ArrayList<Agenda> salida = AgendaPersistence.obtenerAgendaHistoricaDoctor(idDoctor);
            
            for (int i = 0; i < salida.size(); i++) {
                Agenda aux = salida.get(i);
                if(aux.getIdPaciente()!=null && aux.getIdConsulta() != null){
                    Agenda  agendaAux = AgendaPersistence.obtenerAgendaDoctorPaciente(aux);
                    aux.setEspecialidad(agendaAux.getEspecialidad());
                    aux.setTipoConsulta(agendaAux.getTipoConsulta());
                }
            }
            return salida;
            
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la agenda historica del doctor. " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarTurno(Integer idTurno) throws Exception {
        try {
            return AgendaPersistence.eliminarTurno(idTurno);
        } catch (Exception e) {
            throw new Exception("No se pudo eliminar. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Agenda> obtenerMiAgenda(Integer idDoctor) throws Exception {
        try {
            return AgendaPersistence.obtenerMiAgenda(idDoctor);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener Mi Agenda. " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Agenda> obtenerMiAgendaHistorico(Integer idDoctor) throws Exception {
        try {
            return AgendaPersistence.obtenerMiAgendaHistorico(idDoctor);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la Otra Agenda. " + e.getMessage());
       }
    }

    @Override
    public SelectStringValue obtenerConsultorioAgendaTurno(AgendaTurno selectedAgendaTurno) throws Exception {
        try {
            return AgendaPersistence.obtenerConsultorioAgendaTurno(selectedAgendaTurno);
        } catch (Exception e) {
            throw new Exception("Hubo un problema al obtener la Otra Agenda. " + e.getMessage());
       }
    }
}
