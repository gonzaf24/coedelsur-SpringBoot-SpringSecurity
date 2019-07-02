package com.coedelsur.model;

import java.io.Serializable;

public class Barrio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoPais;
	private Integer codigoDepartamento;
	private Integer codigoCiudad;
	private Integer codigoBarrio;
	private String nombreBarrio;

	public Barrio() {
		super();
	}

	public Barrio(Integer codigoPais, Integer codigoDepartamento,Integer codigoCiudad, Integer codigoBarrio, String nombreBarrio) {
		super();
		this.codigoPais = codigoPais;
		this.codigoDepartamento = codigoDepartamento;
		this.codigoCiudad = codigoCiudad;
		this.codigoBarrio = codigoBarrio;
		this.nombreBarrio = nombreBarrio;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public Integer getCodigoDepartamento() {
		return codigoDepartamento;
	}

	public void setCodigoDepartamento(Integer codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	public Integer getCodigoCiudad() {
		return codigoCiudad;
	}

	public void setCodigoCiudad(Integer codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	public Integer getCodigoBarrio() {
		return codigoBarrio;
	}

	public void setCodigoBarrio(Integer codigoBarrio) {
		this.codigoBarrio = codigoBarrio;
	}

	public String getNombreBarrio() {
		return nombreBarrio;
	}

	public void setNombreBarrio(String nombreBarrio) {
		this.nombreBarrio = nombreBarrio;
	}
}
