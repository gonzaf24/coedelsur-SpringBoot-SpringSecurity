package com.coedelsur.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cedulaIdentidad;
    private String nombre;
    private String apellidos;
    private String telefono;
    private Integer idDepartamento;
    private SelectStringValue departamento;
    private String domicilio;
    private Date fechaNacimiento;
    private String email;
    private String foto;

    public Paciente() {
        
    }

    public Paciente(Integer id, Integer cedulaIdentidad, String nombre, String apellidos, String telefono, Integer idDepartamento, String domicilio,
            Date fechaNacimiento) {
        super();
        this.id = id;
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.idDepartamento = idDepartamento;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Paciente(Integer id, Integer cedulaIdentidad, String nombre, String apellidos, String telefono, Integer idDepartamento, String domicilio,
            Date fechaNacimiento, String email, String foto) {
        super();
        this.id = id;
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.idDepartamento = idDepartamento;
        this.domicilio = domicilio;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellidos;
    }

    public String getFechaNacimientoLabel() {
        if (this.fechaNacimiento != null) {
            SimpleDateFormat dateFormatterA = new SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES"));
            return dateFormatterA.format(this.fechaNacimiento);
        }
        return "";
    }

    public SelectStringValue getDepartamento() {
        return departamento;
    }

    public void setDepartamento(SelectStringValue departamento) {
        this.departamento = departamento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
