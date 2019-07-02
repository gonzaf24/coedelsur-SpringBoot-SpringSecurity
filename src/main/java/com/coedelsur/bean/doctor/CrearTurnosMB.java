package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.model.Agenda;
import com.coedelsur.model.AgendaDia;
import com.coedelsur.model.AgendaHoraTurno;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.AgendaServ;
import com.coedelsur.service.ConsultaServ;
import com.coedelsur.service.ConsultorioServ;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.service.PacienteServ;
import com.google.gson.Gson;

@SessionScope
@Component
public class CrearTurnosMB implements Serializable {

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
    private ArrayList<Date> listDiasParaTurnos = new ArrayList<Date>();
    private ArrayList<AgendaDia> listAgendaDia = new ArrayList<AgendaDia>();
    private ArrayList<AgendaHoraTurno> listAgendaHoraTurno = new ArrayList<AgendaHoraTurno>();
    private Date diaSelected;
    private Date today = new Date();
    private AgendaHoraTurno agendaHoraTurno;
    private ArrayList<SelectStringValue> listIntervalos = new ArrayList<SelectStringValue>(Arrays.asList(new SelectStringValue("15 mins.", 15), new SelectStringValue("30 mins.", 30), new SelectStringValue("45 mins.", 45), new SelectStringValue("60 mins.", 60)));
    private SelectStringValue selectedIntervalo;
    private Date desdeHoraSel = obtenerDiaHoyHorayMinutoCero();
    private Date hastaHoraSel = obtenerDiaHoyHorayMinutoCero();
    private ArrayList<ConsultaDoctor> listConsultas = new ArrayList<ConsultaDoctor>();
    private SelectStringValue selectedDoctor;
    private ArrayList<SelectStringValue> listConsultorios = new ArrayList<SelectStringValue>();
    private SelectStringValue selectedConsultorio;
    private ArrayList<Agenda> listaAgendaGenerada = new ArrayList<Agenda>();
    private ArrayList<SelectStringValue> listDoctores = new ArrayList<SelectStringValue>();
    private SimpleDateFormat dateFormatDia = new SimpleDateFormat("EEEE d MMMM , yyyy", new Locale("es", "ES"));
    private SimpleDateFormat dateFormatDiaFormat = new SimpleDateFormat("dd/M/yyyy");
    private SimpleDateFormat dateFormatHora = new SimpleDateFormat ("HH:mm");

    public CrearTurnosMB() {
    }
    
    public void init()throws Exception  {
        setListDoctores(doctorSrv.obtenerListaDoctoresCodigos());
        setListConsultorios(new ArrayList<SelectStringValue>(consultorioServ.obtenerConsultorios()));
        setHastaHoraSel(obtenerDiaHoyHorayMinutoCero());
        setDesdeHoraSel(obtenerDiaHoyHorayMinutoCero());
    }

    public void postCrearTurnos() {
        reset();
    }

    public void agregarDiaCrearTurno() throws Exception {
        String labelDiaSelected = new String(dateFormatDia.format(getDiaSelected()).toUpperCase());
        AgendaDia diaSelected = new AgendaDia(null, labelDiaSelected, getDiaSelected());
        if (existeDiaEnLista(getDiaSelected())) {
            FacesContext.getCurrentInstance().addMessage("messagesCrearTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El d�a ya se encuentra en la lista.", ""));
        } else {
            Collections.reverse(getListAgendaDia());
            getListAgendaDia().add(diaSelected);
            Integer index = getListAgendaDia().indexOf(diaSelected);
            diaSelected.setId(index);
            Collections.reverse(getListAgendaDia());
        }
    }

    public void eliminarDiaCrearTurno(AgendaDia agendaDia) throws Exception {
        if (getListAgendaDia().contains(agendaDia)) {
            getListAgendaDia().remove(agendaDia);
        }
    }

    private boolean existeDiaEnLista(Date diaSelecconado) {
        boolean encontre = false;
        if (getListAgendaDia() != null) {
            for (int i = 0; i < getListAgendaDia().size() && !encontre; i++) {
                AgendaDia agDia = getListAgendaDia().get(i);
                if (agDia.getDia().equals(diaSelecconado)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void generarTurno() {
        AgendaHoraTurno agHoraTurno = new AgendaHoraTurno(getDesdeHoraSel(), getHastaHoraSel(),getSelectedIntervalo());
        if (validarPeriodoTurno(agHoraTurno)) {
            if (validarPerdiodoSolapadoEnLista(agHoraTurno)) {
                agHoraTurno.setLabelHoraDesde(new SimpleDateFormat("HH:mm").format(agHoraTurno.getHoraDesde()));
                agHoraTurno.setLabelHoraHasta(new SimpleDateFormat("HH:mm").format(agHoraTurno.getHoraHasta()));
                getListAgendaHoraTurno().add(agHoraTurno);
                Calendar calendarInstance = Calendar.getInstance();
                calendarInstance.setTime(getDiaSelected());
                int year = calendarInstance.get(Calendar.YEAR);
                int month = calendarInstance.get(Calendar.MONTH);
                int day = calendarInstance.get(Calendar.DAY_OF_MONTH);
                Calendar calendarBD = Calendar.getInstance();
                calendarBD.setTime(getHastaHoraSel());
                calendarBD.set(year, month, day);
                setDesdeHoraSel(calendarBD.getTime());
                Collections.sort(getListAgendaHoraTurno(), new Comparator<AgendaHoraTurno>() {
                    public int compare(AgendaHoraTurno lhs, AgendaHoraTurno rhs) {
                        return lhs.getHoraDesde().compareTo(rhs.getHoraDesde());
                    }
                });                
            } else {
                FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El perdiodo se encuentra solapado con uno ya existente", ""));
            }
        }
    }
  
    public void crearTurnos() throws Exception {
        ArrayList<Agenda> listaAgenda = new ArrayList<Agenda>();
        //lista de dias
        ArrayList<AgendaDia> agendaDia = getListAgendaDia();
        //lista de turnos
        ArrayList<AgendaHoraTurno> agendaHoraTurno = getListAgendaHoraTurno();
        for (int i = 0; i < agendaDia.size(); i++) {
            AgendaDia agendaDiaAux = agendaDia.get(i);
            for (int h = 0; h < agendaHoraTurno.size(); h++) {
                AgendaHoraTurno agendaHoraTurnoAux = agendaHoraTurno.get(h);
                Integer minutosTotales = calcularMinutosTotales(agendaHoraTurnoAux.getHoraDesde(), agendaHoraTurnoAux.getHoraHasta());
                Integer intervalo = agendaHoraTurnoAux.getIntervalo().getCode();
                Integer totalTurnos = minutosTotales / intervalo;
                Date minutosDesdeAux;
                Date minutosHastaAux = null;
                for (int f = 0; f < totalTurnos; f++) {
                    if (f == 0) {
                        minutosDesdeAux = agendaHoraTurnoAux.getHoraDesde();
                    } else {
                        minutosDesdeAux = minutosHastaAux;
                    }
                    
                    Calendar calendarInstance = Calendar.getInstance();
                    calendarInstance.setTime(agendaDiaAux.getDia());
                    int year = calendarInstance.get(Calendar.YEAR);
                    int month = calendarInstance.get(Calendar.MONTH);
                    int day = calendarInstance.get(Calendar.DAY_OF_MONTH);
                    Calendar calendar = Calendar.getInstance();
                    
                    calendar.setTime(minutosDesdeAux);
                    calendar.set(year, month, day);
                    minutosDesdeAux = calendar.getTime();
                    
                    calendar.add(Calendar.MINUTE, intervalo);
                    minutosHastaAux = calendar.getTime();
                    
                    String labelDia = dateFormatDia.format(agendaDiaAux.getDia());
                    String labelHoraDesde = dateFormatHora.format(minutosDesdeAux);
                    String labelHoraHasta = dateFormatHora.format(minutosHastaAux);
                    Agenda agenda = new Agenda(getSelectedDoctor().getCode(), getSelectedConsultorio().getCode(), null, agendaDiaAux.getDia(), minutosDesdeAux, minutosHastaAux, labelDia,labelHoraDesde, labelHoraHasta);
                    listaAgenda.add(agenda);
                }
            }
       }
        
       if(validarInterceptarPeriodos(listaAgenda)){
           setListaAgendaGenerada(listaAgenda);
           //RequestContext.getCurrentInstance().execute("PF('turnosGenerados').show();");
       }
       
    }
    
    public void eliminarSelectedTurnoFromAgenda(Agenda turnoGenerado) throws Exception {
        getListaAgendaGenerada().remove(turnoGenerado);
    }
    
    private boolean validarInterceptarPeriodos(ArrayList<Agenda> listaAgendaCreada) throws Exception {
        ArrayList<Date> arrayDiasConsulta = new ArrayList<Date>();
        for (int i = 0; i < getListAgendaDia().size(); i++) {
            AgendaDia agDia = getListAgendaDia().get(i);
            arrayDiasConsulta.add(agDia.getDia());
        }
        ArrayList<Agenda> listaAgendaBD = agendaServ.obtenerAgendaPorDias(arrayDiasConsulta);
        for (int h = 0 ; h < listaAgendaCreada.size(); h++){
            Agenda agendaCreada =  listaAgendaCreada.get(h);
            for (int i = 0; i < listaAgendaBD.size(); i++) {
                Agenda agendaBDAux = listaAgendaBD.get(i);
                if(agendaCreada.getDia().equals(agendaBDAux.getDia())){
                    if(agendaCreada.getIdConsultorio().equals(agendaBDAux.getIdConsultorio())){
                        
                        Calendar calendarBDDesde = Calendar.getInstance();
                        calendarBDDesde.setTime(agendaBDAux.getHoraDesde());
                        int hourBDDesde = calendarBDDesde.get(Calendar.YEAR);
                        int minuteBDDesde = calendarBDDesde.get(Calendar.MONTH);
                        int secondBDDesde = calendarBDDesde.get(Calendar.DAY_OF_MONTH);
                        
                        Calendar calendarBDHasta = Calendar.getInstance();
                        calendarBDHasta.setTime(agendaBDAux.getHoraHasta());
                        int hourBDHasta = calendarBDHasta.get(Calendar.YEAR);
                        int minuteBDHasta = calendarBDHasta.get(Calendar.MONTH);
                        int secondBDHasta = calendarBDHasta.get(Calendar.DAY_OF_MONTH);
                        
                        Calendar calendarCreadoDesde = Calendar.getInstance();
                        calendarCreadoDesde.setTime(agendaCreada.getHoraDesde());
                        calendarCreadoDesde.set(hourBDDesde, minuteBDDesde, secondBDDesde);
                        Date dateBDDesde =  calendarCreadoDesde.getTime();
                        
                        Calendar calendarCreadoHasta = Calendar.getInstance();
                        calendarCreadoHasta.setTime(agendaCreada.getHoraHasta());
                        calendarCreadoDesde.set(hourBDHasta, minuteBDHasta, secondBDHasta);
                        Date dateBDHasta =  calendarCreadoHasta.getTime();
                        
                        if( dateBDDesde.getTime() < agendaBDAux.getHoraHasta().getTime() && agendaBDAux.getHoraDesde().getTime() < dateBDHasta.getTime()){
                            
                            String horaDesde = dateFormatHora.format(dateBDDesde);
                            String horaHasta = dateFormatHora.format(dateBDHasta);
                            String fechaDia  = dateFormatDiaFormat.format(agendaCreada.getDia());
                            
                            Messages.create("").error().detail("En la fecha "+ fechaDia +" , ya se encuentra el periodo " + horaDesde + " - " + horaHasta +" para ese consultorio").add();
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private Integer calcularMinutosTotales(Date desde, Date hasta) {
        Integer horaDesde = desde.getHours();
        Integer minutoDesde = desde.getMinutes();
        Integer horaHasta = hasta.getHours();
        Integer minutoHasta = hasta.getMinutes();
        Integer minutosTotalesPeriodo = ((horaHasta * 60) + minutoHasta) - ((horaDesde * 60) + minutoDesde);
        return minutosTotalesPeriodo;
    }
    

    public void eliminarTurno(AgendaHoraTurno agendaHoraTurno) {
        getListAgendaHoraTurno().remove(agendaHoraTurno);
    }

    public void clearTurno() {
        setSelectedIntervalo(null);
        setHastaHoraSel(obtenerDiaHoyHorayMinutoCero());
        setDesdeHoraSel(obtenerDiaHoyHorayMinutoCero());
        setListAgendaDia(new ArrayList<AgendaDia>());
        setListaAgendaGenerada(new ArrayList<Agenda>());
        setListAgendaHoraTurno(new ArrayList<AgendaHoraTurno>());
    }

    @SuppressWarnings("deprecation")
    private boolean validarPeriodoTurno(AgendaHoraTurno agHoraTurno) {
        Integer horaDesde = agHoraTurno.getHoraDesde().getHours();
        Integer minutoDesde = agHoraTurno.getHoraDesde().getMinutes();
        Integer horaHasta = agHoraTurno.getHoraHasta().getHours();
        Integer minutoHasta = agHoraTurno.getHoraHasta().getMinutes();
        if (horaHasta < horaDesde) {
            FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor de Hasta no puede ser mayor que Desde", ""));
            return false;
        } else if (horaHasta == horaDesde) {
            if (minutoHasta <= minutoDesde) {
                FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor de Hasta no puede ser mayor o igual que Desde", ""));
                return false;
            }
        }
        Integer minutosTotalesPeriodo = ((horaHasta * 60) + minutoHasta) - ((horaDesde * 60) + minutoDesde);
        Integer intervalo = agHoraTurno.getIntervalo().getCode();
        if (minutosTotalesPeriodo == 0 && intervalo == 0) {
            FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Periodo invalido.", ""));
            return false;
        } else {
            if (intervalo == 0) {
                if (minutosTotalesPeriodo == 0) {
                    FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "El perdiodo no es divisible entre el intervalo.", ""));
                    return false;
                }
            } else if (!(minutosTotalesPeriodo % intervalo == 0)) {
                FacesContext.getCurrentInstance().addMessage("messagesDialogTurnos",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El perdiodo no es divisible entre el intervalo.", ""));
                return false;
            }
        }
        return true;
    }

    private boolean validarPerdiodoSolapadoEnLista(AgendaHoraTurno agHoraTurno) {
        if (getListAgendaHoraTurno() != null) {
            for (int i = 0; i < getListAgendaHoraTurno().size(); i++) {
                AgendaHoraTurno agHoraTurnoAux = getListAgendaHoraTurno().get(i);
                boolean haySolapamiento = isOverlapping(agHoraTurno.getHoraDesde(), agHoraTurno.getHoraHasta(), agHoraTurnoAux.getHoraDesde(),agHoraTurnoAux.getHoraHasta());
                if (haySolapamiento) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        if (start1.equals(end2)) {
            return false;
        } else if (end1.equals(start2)) {
            return false;
        }
        return !start1.after(end2) && !start2.after(end1);
    }

    private Date obtenerDiaHoyHorayMinutoCero() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }
    
    public void clearListaAgendaGenerada(){
        setListaAgendaGenerada(new ArrayList<Agenda>());
    }
    
    public void confirmarTurnos() throws Exception{
        try{
            agendaServ.confirmarPersistirTurnos(getListaAgendaGenerada());
            clearTurno();
            Messages.create("").detail("Se han creado correctamente los tunos.").add();
        }catch (Exception e){            
            FacesContext.getCurrentInstance().addMessage("messagesConfirmarTurnos", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar turno :" + e.getMessage(), ""));
        }
    }

    public ArrayList<Date> getListDiasParaTurnos() {
        return listDiasParaTurnos;
    }

    public void setListDiasParaTurnos(ArrayList<Date> listDiasParaTurnos) {
        this.listDiasParaTurnos = listDiasParaTurnos;
    }

    public Date getDiaSelected() {
        return diaSelected;
    }

    public void setDiaSelected(Date diaSelected) {
        this.diaSelected = diaSelected;
    }

    public String getListDiasParaTurnosAsJSON() {
        return new Gson().toJson(listDiasParaTurnos);
    }

    public ArrayList<AgendaDia> getListAgendaDia() {
        return listAgendaDia;
    }

    public void setListAgendaDia(ArrayList<AgendaDia> listAgendaDia) {
        this.listAgendaDia = listAgendaDia;
    }

    public ArrayList<AgendaHoraTurno> getListAgendaHoraTurno() {
        return listAgendaHoraTurno;
    }

    public void setListAgendaHoraTurno(ArrayList<AgendaHoraTurno> listAgendaHoraTurno) {
        this.listAgendaHoraTurno = listAgendaHoraTurno;
    }

    public AgendaHoraTurno getAgendaHoraTurno() {
        return agendaHoraTurno;
    }

    public void setAgendaHoraTurno(AgendaHoraTurno agendaHoraTurno) {
        this.agendaHoraTurno = agendaHoraTurno;
    }

    public ArrayList<SelectStringValue> getListIntervalos() {
        return listIntervalos;
    }

    public void setListIntervalos(ArrayList<SelectStringValue> listIntervalos) {
        this.listIntervalos = listIntervalos;
    }

    public SelectStringValue getSelectedIntervalo() {
        return selectedIntervalo;
    }

    public void setSelectedIntervalo(SelectStringValue selectedIntervalo) {
        this.selectedIntervalo = selectedIntervalo;
    }

    public Date getDesdeHoraSel() {
        return desdeHoraSel;
    }

    public void setDesdeHoraSel(Date desdeHoraSel) {
        this.desdeHoraSel = desdeHoraSel;
    }

    public Date getHastaHoraSel() {
        return hastaHoraSel;
    }

    public void setHastaHoraSel(Date hastaHoraSel) {
        this.hastaHoraSel = hastaHoraSel;
    }

    public ArrayList<ConsultaDoctor> getListConsultas() {
        return listConsultas;
    }

    public void setListConsultas(ArrayList<ConsultaDoctor> listConsultas) {
        this.listConsultas = listConsultas;
    }

    public SelectStringValue getSelectedDoctor() {
        return selectedDoctor;
    }

    public void setSelectedDoctor(SelectStringValue selectedDoctor) {
        this.selectedDoctor = selectedDoctor;
    }

    public ArrayList<SelectStringValue> getListConsultorios() {
        return listConsultorios;
    }

    public void setListConsultorios(ArrayList<SelectStringValue> listConsultorios) {
        this.listConsultorios = listConsultorios;
    }

    public SelectStringValue getSelectedConsultorio() {
        return selectedConsultorio;
    }

    public void setSelectedConsultorio(SelectStringValue selectedConsultorio) {
        this.selectedConsultorio = selectedConsultorio;
    }

    public Date getToday() {
        return today;
    }

    public ArrayList<Agenda> getListaAgendaGenerada() {
        return listaAgendaGenerada;
    }

    public void setListaAgendaGenerada(ArrayList<Agenda> listaAgendaGenerada) {
        this.listaAgendaGenerada = listaAgendaGenerada;
    }

    public ArrayList<SelectStringValue> getListDoctores() {
        return listDoctores;
    }

    public void setListDoctores(ArrayList<SelectStringValue> listDoctores) {
        this.listDoctores = listDoctores;
    }
    
    public void reset(){
        this.listDiasParaTurnos = null;
        this.diaSelected = null;
        this.today = new Date();
        this.agendaHoraTurno = null;
        this.selectedIntervalo = null;
        this.selectedDoctor = null;
        this.selectedConsultorio= null;
        this.desdeHoraSel = obtenerDiaHoyHorayMinutoCero();
        this.hastaHoraSel = obtenerDiaHoyHorayMinutoCero();
        this.listaAgendaGenerada = new ArrayList<Agenda>();
        this.listDoctores= new ArrayList<SelectStringValue>();
        this.listConsultas  = new ArrayList<ConsultaDoctor>();
        this.listConsultorios = new ArrayList<SelectStringValue>();
        this.listAgendaDia = new ArrayList<AgendaDia>();
        this.listAgendaHoraTurno = new ArrayList<AgendaHoraTurno>();
    }
}
