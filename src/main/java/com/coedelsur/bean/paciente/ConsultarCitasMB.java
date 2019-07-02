package com.coedelsur.bean.paciente;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.Consulta;
import com.coedelsur.model.Paciente;
import com.coedelsur.service.AgendaServ;

@SessionScope
@Component
public class ConsultarCitasMB implements Serializable {

	@Inject
    AgendaServ agendaServ;
	@Inject
    SessionMB sessionMB;
	
    private static final long serialVersionUID = 1L;
    private ArrayList<Consulta> listMisConsultas;
    private Consulta selectedConsulta;
    private Paciente paciente;

    public ConsultarCitasMB() {
    }
    
    public void init() throws Exception {
        setListMisConsultas(new ArrayList<Consulta>(agendaServ.obtenerConsultasPaciente(sessionMB.getPaciente().getId())));
        setPaciente(sessionMB.getPaciente());
    }

    public ArrayList<Consulta> getListMisConsultas() {
        return listMisConsultas;
    }

    public void setListMisConsultas(ArrayList<Consulta> listMisConsultas) {
        this.listMisConsultas = listMisConsultas;
    }

    public Consulta getSelectedConsulta() {
        return selectedConsulta;
    }

    public void setSelectedConsulta(Consulta selectedConsulta) {
        this.selectedConsulta = selectedConsulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
