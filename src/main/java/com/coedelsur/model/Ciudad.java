package com.coedelsur.model;

import java.io.Serializable;

public class Ciudad implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer codigoPais;
	private Integer codigoDepartamento;
	private Integer codigoCiudad;
	private String nombreCiudad;

	public Ciudad() {
		super();
	}

	public Ciudad(Integer codigoPais, Integer codigoDepartamento,
			Integer codigoCiudad, String nombreCiudad) {
		super();
		this.codigoPais = codigoPais;
		this.codigoDepartamento = codigoDepartamento;
		this.codigoCiudad = codigoCiudad;
		this.nombreCiudad = nombreCiudad;
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

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

}
