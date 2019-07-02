package com.coedelsur.model;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cedulaIdentidad;
    private String nombre;
    private String apellidos;
    private SelectStringString sexo;
    private String telefono;
    private String domicilio;
    private SelectStringValue departamento;
    private SelectStringValue especialidad;
    private Integer idEspecialidad2;
    private Integer idEspecialidad3;
    private Boolean activo;
    private String foto;

    public Doctor() {
    }
    
    

    public Doctor(Integer id, Integer cedulaIdentidad, String nombre, String apellidos, SelectStringString sexo, String telefono, String domicilio,
            SelectStringValue departamento, SelectStringValue especialidad, Boolean activo, String foto) {
        super();
        this.id = id;
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.departamento = departamento;
        this.especialidad = especialidad;
        this.activo = activo;
        this.foto = foto;
    }



    public Doctor(Integer id, Integer cedulaIdentidad, String nombre, String apellidos, SelectStringString sexo, String telefono, String domicilio,
            SelectStringValue departamento, SelectStringValue especialidad, Boolean activo) {
        super();
        this.id = id;
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.departamento = departamento;
        this.especialidad = especialidad;
        this.activo = activo;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public SelectStringValue getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(SelectStringValue especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getIdEspecialidad2() {
        return idEspecialidad2;
    }

    public void setIdEspecialidad2(Integer idEspecialidad2) {
        this.idEspecialidad2 = idEspecialidad2;
    }

    public Integer getIdEspecialidad3() {
        return idEspecialidad3;
    }

    public void setIdEspecialidad3(Integer idEspecialidad3) {
        this.idEspecialidad3 = idEspecialidad3;
    }

    public String getNombreCompleto() {
        return this.sexo.getLabel() + " " + this.nombre + " " + this.getApellidos();
    }
    
    public String getNombreCompletoIncidental() {
        return this.nombre + " " + this.getApellidos();
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public SelectStringValue getDepartamento() {
        return departamento;
    }

    public void setDepartamento(SelectStringValue departamento) {
        this.departamento = departamento;
    }

    public SelectStringString getSexo() {
        return sexo;
    }

    public void setSexo(SelectStringString sexo) {
        this.sexo = sexo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
