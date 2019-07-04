package com.coedelsur.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.database.persistence.LoginPersistence;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.model.Usuario;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.service.PacienteServ;
import com.coedelsur.service.UsuarioServ;

@SessionScope
@Component
public class SessionMB implements Serializable {

	@Inject
	UsuarioServ usuaurioServ;
	
	@Inject
	DoctorServ doctorServ;
	
	@Inject
	PacienteServ pacienteServ;
	
	@Inject
	ComboUtilsServ comboUtilsServ;
	
	private static final long serialVersionUID = 1L;
	private String currentUser;
	private Paciente paciente;
	private Doctor doctor;
	private ArrayList<SelectStringValue> departamentoList;
	private ArrayList<SelectStringValue> tipoConsultaList;
	private ArrayList<SelectStringValue> especialidadesList;
	private ArrayList<Doctor> doctoresList;
	private ArrayList<String> logueosList;

	@PostConstruct
	public void init() throws Exception {
		currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario user = LoginPersistence.obtenerUsuario(currentUser.toLowerCase());
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> grantedAuths = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication(). getAuthorities();
		if(grantedAuths.get(0).getAuthority().equalsIgnoreCase("ROLE_DOCTOR")) {
			doctor = doctorServ.obtenerDoctor(user.getId());
		}else{
			paciente = pacienteServ.obtenerPaciente(user.getId());
		}
		setDepartamentoList(comboUtilsServ.obtenerDepartamentos());
        setTipoConsultaList(comboUtilsServ.obtenerTipoConsultas());
        setEspecialidadesList(comboUtilsServ.obtenerEspecialdades());
        setLogueosList(usuaurioServ.obtenerLogueos());
	}

	public void refrescarLista() throws Exception {
		logueosList = new ArrayList<>(usuaurioServ.obtenerLogueos());

	}

	public void registrarLogueo() throws Exception {
		usuaurioServ.registrarLogueo(getCurrentUser());
		logueosList = new ArrayList<>(usuaurioServ.obtenerLogueos());

	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public ArrayList<String> getLogueosList() {
		return logueosList;
	}

	public void setLogueosList(ArrayList<String> logueosList) {
		this.logueosList = logueosList;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
}
