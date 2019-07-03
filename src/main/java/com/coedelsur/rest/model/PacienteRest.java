package com.coedelsur.rest.model;

import java.io.Serializable;
import java.util.Date;

public class PacienteRest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private Integer cedulaIdentidad;
	private String nombre;
	private String apellidos;
	private String telefono;
	private Integer idDepartamento;
	private String domicilio;
	private Date fechaNacimiento;

	public PacienteRest() {

	}

	public Integer getCedulaIdentidad() {
		return cedulaIdentidad;
	}

	public void setCedulaIdentidad(Integer cedulaIdentidad) {
		this.cedulaIdentidad = cedulaIdentidad;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
