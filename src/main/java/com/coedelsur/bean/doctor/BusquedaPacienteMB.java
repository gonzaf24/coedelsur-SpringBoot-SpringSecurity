package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.model.Paciente;
import com.coedelsur.service.PacienteServ;

@SessionScope
@Component
public class BusquedaPacienteMB implements Serializable {

	@Inject
	PacienteServ pacienteServ;

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private Integer ci;
	private Integer selectedIdPaciente;
	private ArrayList<Paciente> pacientesList = new ArrayList<Paciente>();

	public BusquedaPacienteMB() {
	}

	public Integer getSelectedIdPaciente() {
		return selectedIdPaciente;
	}
	

	public void setSelectedIdPaciente(Integer selectedIdPaciente) {
		this.selectedIdPaciente = selectedIdPaciente;
	}

	public void buscarPacientesFiltros() throws Exception {
		pacientesList = pacienteServ.obtenerPacientesFiltro(null, null, null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public ArrayList<Paciente> getPacientesList() {
		return pacientesList;
	}

	public void setPacientesList(ArrayList<Paciente> pacientesList) {
		this.pacientesList = pacientesList;
	}
}
