package com.coedelsur.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.Consulta;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;

public interface AgendaServ {

    public List<AgendaDia> obtenerDiasStep2(Doctor doc) throws Exception;

    public List<AgendaTurno> obtenerTurnosStep3(Doctor doc, AgendaDia agTur) throws Exception;

    boolean reservaTurno(AgendaTurno agTurno, Paciente paciente, ConsultaDoctor consultaDoctor) throws Exception;

    public boolean cancelarReservaTurno(AgendaTurno selectedAgendaTurno) throws Exception;

    public boolean turnoDisponible(AgendaTurno selectedAgendaTurno) throws Exception;

    public List<Consulta> obtenerConsultasPaciente(Integer idPaciente) throws Exception;

    public ArrayList<Agenda> obtenerAgendaPorDias(ArrayList<Date> arrayList)throws Exception;

    public boolean confirmarPersistirTurnos(ArrayList<Agenda> listaAgendaGenerada)throws Exception;

    public ArrayList<Agenda> obtenerAgendaDoctor(Integer idDoctor)throws Exception;

    public ArrayList<Agenda>  obtenerAgendaDoctorHistorico(Integer idDoctor)throws Exception;

    public boolean eliminarTurno(Integer isTurno)throws Exception;
    
    public ArrayList<Agenda> obtenerMiAgenda(Integer idDoctor)throws Exception;

    public ArrayList<Agenda>  obtenerMiAgendaHistorico(Integer idDoctor)throws Exception;

    public SelectStringValue obtenerConsultorioAgendaTurno(AgendaTurno selectedAgendaTurno)throws Exception;
    
}
