package com.coedelsur.bean.paciente;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.Pago;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.AgendaServ;
import com.coedelsur.service.ComboUtilsServ;

@SessionScope
@Component
public class SolicitarCitaMB implements Serializable {
	
	@Inject
	ComboUtilsServ comboUtilsServ;
	@Inject
	AgendaServ agendaServ;
	@Inject
	SessionMB sessionMB;

	private static final long serialVersionUID = 1L;
	private SelectStringValue selectedTipoConsulta;
	private SelectStringValue selectedEspecialidad;
	private AgendaDia selectedAgendaDia;
	private AgendaTurno selectedAgendaTurno;
	private Doctor selectedDoctor;
	private ConsultaDoctor selectedConsulta;
	private Boolean disableStep1 = true;
	private Boolean disableStep2 = true;
	private Boolean disableStep3 = true;
	private Boolean disableStep4 = true;
	private Boolean activeStep1 = false;
	private Boolean activeStep2 = false;
	private Boolean activeStep3 = false;
	private Boolean activeStep4 = false;
	private Integer stepIndex = 0;
	private ArrayList<AgendaDia> listDiasAgendaDoctor;
	private ArrayList<AgendaTurno> listTurnosAgendaDoctor;
	private String tipoPago;
	private Pago pago;
	private Boolean cancelarConsulta = true;
	private SelectStringValue consultorioAgenda;
	private ArrayList<SelectStringValue> departamentoList;
	private ArrayList<SelectStringValue> tipoConsultaList;
	private ArrayList<SelectStringValue> especialidadesList;
	private ArrayList<Doctor> doctoresList;

	public SolicitarCitaMB() {
	}

	@PostConstruct
	public void init() throws Exception {
		departamentoList = sessionMB.getDepartamentoList();
		doctoresList = sessionMB.getDoctoresList();
		tipoConsultaList = comboUtilsServ.obtenerTipoConsultasSolicitarCita();
		stepIndex = 0;
		if (getSelectedDoctor() != null) {
			setDisableStep1(false);
		} else {
			setDisableStep1(true);
		}
	}

	public void init1() throws Exception {
		stepIndex = 0;
		selectedEspecialidad = null;
		selectedDoctor = null ;
		if (getSelectedDoctor() != null) {
			setDisableStep1(false);
		} else {
			setDisableStep1(true);
		}
	}

	public void init2() throws Exception {
		stepIndex = 1;
		disableStep2 = true;
		selectedAgendaDia = null;
	}
	
	public void obtenerDiasAgendaDoctor() throws Exception {
		listDiasAgendaDoctor = new ArrayList<AgendaDia>(agendaServ.obtenerDiasStep2(selectedDoctor));
	}

	public void init3() throws Exception {
		stepIndex = 2;
		disableStep3 = true;
		selectedAgendaTurno = null ;
	}
	
	public void obtenerTurnosAgendaDoctor() throws Exception{
		listTurnosAgendaDoctor = new ArrayList<AgendaTurno>(agendaServ.obtenerTurnosStep3(getSelectedDoctor(), selectedAgendaDia));
	}

	public void init4() throws Exception {
		stepIndex = 3;
		disableStep4 = true;
	}
	
	public void obtenerConsultorioYReservarTurno()throws Exception{
		setConsultorioAgenda(agendaServ.obtenerConsultorioAgendaTurno(selectedAgendaTurno));
		agendaServ.reservaTurno(selectedAgendaTurno, (Paciente) sessionMB.getPaciente(), selectedConsulta);
	}

	public void postSolicitarCitaStep4Handler() throws Exception {
 
		UIComponent component = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
        
		if ( (!component.getId().equals(new String ("confirmarCitaBtn")) && !component.getId().equals(new String ("j_idt2")) ) && selectedAgendaTurno != null && selectedConsulta != null ) {
			agendaServ.cancelarReservaTurno(selectedAgendaTurno);
            resetForm();
		}
		
	}
	
	public void cancelarSolicitarCitaStep4Handler() throws Exception {
			agendaServ.cancelarReservaTurno(selectedAgendaTurno);
            resetForm();
	}

	public void fillTipoConsulta() throws Exception {
        setTipoConsultaList(comboUtilsServ.obtenerTipoConsultasSolicitarCita());
        especialidadesList = null;
        setDoctoresList(null);
        setSelectedDoctor(null);
        setDisableStep1(true);
    }

	public void fillEspecialidadPorTipoConsulta() {
        if (selectedTipoConsulta != null) {
            especialidadesList = comboUtilsServ.obtenerEspecialidadesPorTipoConsultaSolicitarCita(selectedTipoConsulta.getCode());
        } else {
            especialidadesList = new ArrayList<SelectStringValue>();
        }
        setDoctoresList(null);
        setSelectedDoctor(null);
        setDisableStep1(true);
    }

	public void fillDoctoresPorEspecialidad() throws Exception {
		if (selectedEspecialidad != null) {
			setSelectedConsulta(comboUtilsServ.obtenerConsultaSolicitarCita(selectedTipoConsulta.getCode(),
					selectedEspecialidad.getCode()));
			setDoctoresList(comboUtilsServ.obtenerDoctores(selectedEspecialidad.getCode()));
		} else {
			setDoctoresList(new ArrayList<Doctor>());
		}
		setSelectedDoctor(null);
		setDisableStep1(true);
	}

	public void fillTarjetaDoctor() {

		if (getSelectedDoctor() != null) {
			setDisableStep1(false);
		} else {
			setDisableStep1(true);
		}
	}

	public void selectDia(AgendaDia agendaDia) {
		selectedAgendaDia = agendaDia;
		disableStep2 = false ;
	}

	public void selectTurno() {
		if (selectedAgendaTurno != null) {
			setDisableStep3(false);
		} else {
			setDisableStep3(true);
		}
	}

	public void selectTipoPago() {
		if (getTipoPago() != null && getTipoPago().equalsIgnoreCase(new String("EFECTIVO"))) {
			setDisableStep4(false);
		} else {
			setDisableStep4(true);
		}
	}

	public String confirmarReservaTurno() {
		setCancelarConsulta(false);
		return "succes";
	}

	public String onTimeoutStep4() throws Exception {
		if (selectedAgendaTurno != null) {
			agendaServ.cancelarReservaTurno(selectedAgendaTurno);
		}
		resetForm();
		return "timeOutTransition";
	}

	public void confirmacionCitaFinStep5() {
		resetForm();
	}

	public String cancelarStep4() throws Exception {
		agendaServ.cancelarReservaTurno(selectedAgendaTurno);
		resetForm();
		return "cancelar";
	}

	public String turnoDisponible() throws Exception {
		boolean salida = agendaServ.turnoDisponible(selectedAgendaTurno);
		if (!salida) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"El turno ya no est� disponible, seleccione otro.", ""));
			return "error";
		}
		return "success";
	}

	public SelectStringValue getSelectedTipoConsulta() {
		return selectedTipoConsulta;
	}

	public void setSelectedTipoConsulta(SelectStringValue selectedTipoConsulta) {
		this.selectedTipoConsulta = selectedTipoConsulta;
	}

	public SelectStringValue getSelectedEspecialidad() {
		return selectedEspecialidad;
	}

	public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
		this.selectedEspecialidad = selectedEspecialidad;
	}

	public Doctor getSelectedDoctor() {
		return selectedDoctor;
	}

	public void setSelectedDoctor(Doctor selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}

	public Boolean getDisableStep1() {
		return disableStep1;
	}

	public void setDisableStep1(Boolean disableStep1) {
		this.disableStep1 = disableStep1;
	}

	public Boolean getActiveStep1() {
		return activeStep1;
	}

	public void setActiveStep1(Boolean activeStep1) {
		this.activeStep1 = activeStep1;
	}

	public Boolean getActiveStep2() {
		return activeStep2;
	}

	public void setActiveStep2(Boolean activeStep2) {
		this.activeStep2 = activeStep2;
	}

	public Boolean getActiveStep3() {
		return activeStep3;
	}

	public void setActiveStep3(Boolean activeStep3) {
		this.activeStep3 = activeStep3;
	}

	public Boolean getActiveStep4() {
		return activeStep4;
	}

	public void setActiveStep4(Boolean activeStep4) {
		this.activeStep4 = activeStep4;
	}

	public Integer getStepIndex() {
		return stepIndex;
	}

	public void setStepIndex(Integer stepIndex) {
		this.stepIndex = stepIndex;
	}

	public ArrayList<AgendaDia> getListDiasAgendaDoctor() {
		return listDiasAgendaDoctor;
	}

	public void setListDiasAgendaDoctor(ArrayList<AgendaDia> listDiasAgendaDoctor) {
		this.listDiasAgendaDoctor = listDiasAgendaDoctor;
	}

	public AgendaDia getSelectedAgendaDia() {
		return selectedAgendaDia;
	}

	public void setSelectedAgendaDia(AgendaDia selectedAgendaDia) {
		this.selectedAgendaDia = selectedAgendaDia;
	}

	public Boolean getDisableStep2() {
		return disableStep2;
	}

	public void setDisableStep2(Boolean disableStep2) {
		this.disableStep2 = disableStep2;
	}

	public Boolean getDisableStep3() {
		return disableStep3;
	}

	public void setDisableStep3(Boolean disableStep3) {
		this.disableStep3 = disableStep3;
	}

	public Boolean getDisableStep4() {
		return disableStep4;
	}

	public void setDisableStep4(Boolean disableStep4) {
		this.disableStep4 = disableStep4;
	}

	public AgendaTurno getSelectedAgendaTurno() {
		return selectedAgendaTurno;
	}

	public void setSelectedAgendaTurno(AgendaTurno selectedAgendaTurno) {
		this.selectedAgendaTurno = selectedAgendaTurno;
	}

	public ArrayList<AgendaTurno> getListTurnosAgendaDoctor() {
		return listTurnosAgendaDoctor;
	}

	public void setListTurnosAgendaDoctor(ArrayList<AgendaTurno> listTurnosAgendaDoctor) {
		this.listTurnosAgendaDoctor = listTurnosAgendaDoctor;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Boolean getCancelarConsulta() {
		return cancelarConsulta;
	}

	public void setCancelarConsulta(Boolean cancelarConsulta) {
		this.cancelarConsulta = cancelarConsulta;
	}

	public ConsultaDoctor getSelectedConsulta() {
		return selectedConsulta;
	}

	public void setSelectedConsulta(ConsultaDoctor selectedConsulta) {
		this.selectedConsulta = selectedConsulta;
	}

	public SelectStringValue getConsultorioAgenda() {
		return consultorioAgenda;
	}

	public void setConsultorioAgenda(SelectStringValue consultorioAgenda) {
		this.consultorioAgenda = consultorioAgenda;
	}

	public ArrayList<SelectStringValue> getDepartamentoList() {
		return departamentoList;
	}

	public void setDepartamentoList(ArrayList<SelectStringValue> departamentoList) {
		this.departamentoList = departamentoList;
	}

	public ArrayList<SelectStringValue> getTipoConsultaList() {
		return tipoConsultaList;
	}

	public void setTipoConsultaList(ArrayList<SelectStringValue> tipoConsultaList) {
		this.tipoConsultaList = tipoConsultaList;
	}

	public ArrayList<SelectStringValue> getEspecialidadesList() {
		return especialidadesList;
	}

	public void setEspecialidadesList(ArrayList<SelectStringValue> especialidadesList) {
		this.especialidadesList = especialidadesList;
	}

	public ArrayList<Doctor> getDoctoresList() {
		return doctoresList;
	}

	public void setDoctoresList(ArrayList<Doctor> doctoresList) {
		this.doctoresList = doctoresList;
	}

	public void resetForm() {
		this.selectedAgendaDia = null;
		this.selectedAgendaTurno = null;
		this.selectedDoctor = null;
		this.selectedEspecialidad = null;
		this.selectedTipoConsulta = null;
		this.tipoPago = null;
		this.selectedConsulta = null;
		this.consultorioAgenda = null;
	}

}
