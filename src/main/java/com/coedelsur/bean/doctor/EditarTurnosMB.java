package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.AgendaServ;
import com.coedelsur.service.ConsultaServ;
import com.coedelsur.service.ConsultorioServ;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.service.PacienteServ;

@SessionScope
@Component
public class EditarTurnosMB implements Serializable {

	@Inject
	PacienteServ pacienteServ;
	@Inject
	ConsultorioServ consultorioServ;
	@Inject
	ConsultaServ consultaServ;
	@Inject
	DoctorServ doctorSrv;
	@Inject
	AgendaServ agendaServ;

	private static final long serialVersionUID = 1L;
	private ArrayList<Agenda> listaAgendaEditarTurno = new ArrayList<Agenda>();
	private Paciente selectedPaciente;
	private Agenda selectedTurnoEliminar;
	private SelectStringValue selectedDoctor;
	private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();

	public EditarTurnosMB() {

	}

	public void init() throws Exception {
		listDoctores = doctorSrv.obtenerListaDoctoresCodigos();
	}

	public void obtenerHistoricoEditarTurno() throws Exception {

		listaAgendaEditarTurno = agendaServ.obtenerAgendaDoctorHistorico(selectedDoctor.getCode());
	}

	public void fillListaAgendaEditarTurno() throws Exception {

		listaAgendaEditarTurno = agendaServ.obtenerAgendaDoctorHistorico(selectedDoctor.getCode());
	}

	public void obtenerPaciente(Integer id) throws Exception {
		setSelectedPaciente(pacienteServ.obtenerPaciente(id));
	}

	public void eliminarTurno(Agenda agenda) throws Exception {
		try {

			boolean eliminado = agendaServ.eliminarTurno(agenda.getId());
			if (eliminado) {
				getListaAgendaEditarTurno().remove(agenda);
				FacesContext.getCurrentInstance().addMessage("messagesEditarTurnos",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el turno.", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage("messagesEditarTurnos",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tuno no se ha podido eliminar", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesEditarTurnos",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el turno :" + e.getMessage(), ""));
		}

	}

	public void postEditarTurno() {
		reset();
	}

	public ArrayList<Agenda> getListaAgendaEditarTurno() {
		return listaAgendaEditarTurno;
	}

	public void setListaAgendaEditarTurno(ArrayList<Agenda> listaAgendaEditarTurno) {
		this.listaAgendaEditarTurno = listaAgendaEditarTurno;
	}

	public Paciente getSelectedPaciente() {
		return selectedPaciente;
	}

	public void setSelectedPaciente(Paciente selectedPaciente) {
		this.selectedPaciente = selectedPaciente;
	}

	public Agenda getSelectedTurnoEliminar() {
		return selectedTurnoEliminar;
	}

	public void setSelectedTurnoEliminar(Agenda selectedTurnoEliminar) {
		this.selectedTurnoEliminar = selectedTurnoEliminar;
	}

	public SelectStringValue getSelectedDoctor() {
		return selectedDoctor;
	}

	public void setSelectedDoctor(SelectStringValue selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}

	public ArrayList<SelectStringValue> getListDoctores() {
		return listDoctores;
	}

	public void setListDoctores(ArrayList<SelectStringValue> listDoctores) {
		this.listDoctores = listDoctores;
	}

	public void reset() {
		this.listaAgendaEditarTurno = new ArrayList<Agenda>();
		this.listDoctores = new ArrayList<SelectStringValue>();
		this.selectedDoctor = null;
		this.selectedPaciente = null;
		this.selectedTurnoEliminar = null;
	}
}
