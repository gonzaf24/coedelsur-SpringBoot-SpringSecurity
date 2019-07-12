package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.Agenda;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.AgendaServ;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.service.HistoriaClinicaServ;
import com.coedelsur.service.PacienteServ;

@SessionScope
@Component
public class MiAgendaMB implements Serializable {

	@Inject
	DoctorServ doctorSrv;
	@Inject
	AgendaServ agendaServ;
	@Inject
	PacienteServ pacienteServ;
	@Inject
	HistoriaClinicaServ historiaClinicaServ;
	@Inject
	SessionMB sessionMB;
	
    private static final long serialVersionUID = 1L;
    private ArrayList<Agenda> listMiAgenda = new ArrayList<Agenda>();
    private ScheduleModel eventModel;
    private DefaultScheduleEvent addEvent;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private SelectStringValue selectedDoctor;
    private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();
    private ArrayList<HistoriaClinica> historiaClinicaList = new ArrayList<HistoriaClinica>();
    private Paciente paciente;
    private Agenda agenda;

    public MiAgendaMB() {
    }
    
	public void init() throws Exception  {
        try {
            eventModel = new DefaultScheduleModel();
          
            listMiAgenda = agendaServ.obtenerMiAgenda(sessionMB.getDoctor().getId());
            
            for (int i = 0; i < listMiAgenda.size(); i++) {
                
                Agenda agenda = listMiAgenda.get(i);
                
                Calendar calDesde = Calendar.getInstance();
                calDesde.setTime(agenda.getDia());
                Calendar calHoraDesde = Calendar.getInstance();
                calHoraDesde.setTime(agenda.getHoraDesde());
                calDesde.set(Calendar.HOUR, calHoraDesde.get(Calendar.HOUR_OF_DAY));
                calDesde.set(Calendar.MINUTE, calHoraDesde.get(Calendar.MINUTE));
                Date horaDesde = calDesde.getTime();
                
                Calendar calHasta = Calendar.getInstance();
                calHasta.setTime(agenda.getDia());
                Calendar calHoraHasta = Calendar.getInstance();
                calHoraHasta.setTime(agenda.getHoraHasta());
                calHasta.set(Calendar.HOUR, calHoraHasta.get(Calendar.HOUR_OF_DAY));
                calHasta.set(Calendar.MINUTE, calHoraHasta.get(Calendar.MINUTE));
                Date horaHasta = calHasta.getTime();
                
                String nombreCompletoPaciente = agenda.getPaciente().getNombre() + " " + agenda.getPaciente().getApellidos() + "  Consultorio: " + agenda.getConsultorio().getLabel();
                
                eventModel.addEvent(new DefaultScheduleEvent(nombreCompletoPaciente, horaDesde, horaHasta, agenda));
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void postMiAgenda() throws Exception {
        reset();
    }
    
    public DefaultScheduleEvent getAddEvent() {
        return addEvent;
    }

    public void setAddEvent(DefaultScheduleEvent addEvent) {
        this.addEvent = addEvent;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); //set random day of month
        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    
    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) throws Exception {
        event = (ScheduleEvent) selectEvent.getObject();
        Agenda agenda = (Agenda) event.getData();
        setAgenda(agenda);
        setPaciente(pacienteServ.obtenerPaciente(agenda.getIdPaciente()));
        setHistoriaClinicaList(historiaClinicaServ.obtenerHistoriaClinica(getPaciente()));
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:"
                + event.getMinuteDelta());
        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:"
                + event.getMinuteDelta());
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public ArrayList<Agenda> getListMiAgenda() {
        return listMiAgenda;
    }

    public void setListMiAgenda(ArrayList<Agenda> listMiAgenda) {
        this.listMiAgenda = listMiAgenda;
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

    public ArrayList<HistoriaClinica> getHistoriaClinicaList() {
        return historiaClinicaList;
    }

    public void setHistoriaClinicaList(ArrayList<HistoriaClinica> historiaClinicaList) {
        this.historiaClinicaList = historiaClinicaList;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public void reset() {
        this.eventModel = null;
        this.addEvent = null;
        this.event = null;
        this.selectedDoctor = null;
        this.paciente = null;
        this.agenda = null;
        this.listDoctores = new ArrayList<SelectStringValue>();
        this.listMiAgenda = new ArrayList<Agenda>();
        this.historiaClinicaList = new ArrayList<HistoriaClinica>();
    }
}
