package com.coedelsur.model;

import java.io.Serializable;

public class DefaultGenericCode implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigo;
	private String descripcion;
	private String vigente;

	public DefaultGenericCode() {
		super();
	}

	public DefaultGenericCode(Integer codigo, String descripcion, String vigente) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.vigente = vigente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getVigente() {
		return vigente;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	
}
