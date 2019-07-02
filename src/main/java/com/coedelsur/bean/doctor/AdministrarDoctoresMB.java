package com.coedelsur.bean.doctor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringString;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.model.Usuario;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.ConsultaServ;
import com.coedelsur.service.DoctorServ;

import sun.misc.BASE64Encoder;

@SessionScope
@Component
public class AdministrarDoctoresMB implements Serializable {
	@Inject
	ComboUtilsServ comboUtilsServ;
	@Inject
	DoctorServ doctorSrv;
	@Inject
	ConsultaServ consultaServ;
	@Inject
	SessionMB sessionMB;

	private static final long serialVersionUID = 1L;
	private ArrayList<Doctor> adminDoctoresList = new ArrayList<Doctor>();
	private ArrayList<SelectStringString> sexoList = new ArrayList<SelectStringString>(Arrays.asList(new SelectStringString("Dr.", "H"), new SelectStringString("Dra.", "M")));
	private Doctor doctorSelectedEdicion = new Doctor();
	private Doctor nuevoDoc = new Doctor();
	private Usuario usuario = new Usuario();
	private SelectStringValue selectedDepartamento = new SelectStringValue();
	private SelectStringValue selectedEspecialidad = new SelectStringValue();
	private SelectStringString selectedSexo = new SelectStringString();
	private String nombreArchivoFotoDoc;
	private String pass1;
	private String pass2;
	private ArrayList<SelectStringValue> departamentoList;
	private ArrayList<SelectStringValue> especialidadesList;

	public AdministrarDoctoresMB() {
	}

	public void init() throws Exception {
		try {

			setAdminDoctoresList(doctorSrv.obtenerListaDoctores());
			setDepartamentoList(comboUtilsServ.obtenerDepartamentos());
			setEspecialidadesList(comboUtilsServ.obtenerEspecialdades());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hubo error al obtener la lista de doctores :" + e.getMessage(), ""));
		}
	}

	public void crearGuardarDoctor() throws Exception {
		try {

			getNuevoDoc().setDepartamento(getSelectedDepartamento());
			getNuevoDoc().setSexo(getSelectedSexo());
			getNuevoDoc().setEspecialidad(getSelectedEspecialidad());
			getUsuario().setPassword(getPass1());
			doctorSrv.crearUsuarioDoctor(getNuevoDoc(), getUsuario());
			resetearForm();
			FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Se han creado correctamente el usuario del Doctor.", ""));
			resetearForm();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hubo error al registrar el doctor :" + e.getMessage(), ""));
		}
	}

	public void confirmarEdicionDoctor() throws Exception {
		try {

			doctorSrv.editarDatosDoctor(getDoctorSelectedEdicion());
			resetearForm();
			FacesContext.getCurrentInstance().addMessage("messagesGeneralEdicion", new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Se ha editado correctamente los datos del doctor.", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hubo error al guardar los datos del doctor :" + e.getMessage(), ""));
		}

	}

	public void selectEditar(Doctor doc) throws Exception {
		try {

			setDoctorSelectedEdicion(doc);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al :" + e.getMessage(), ""));
		}

	}
	
	 public void uploadImagenDoc(FileUploadEvent event) throws Exception {
	        try {
	            byte[] image = event.getFile().getContents();
	            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
	            int width = bi.getWidth();
	            int height = bi.getHeight();
	            if (width <= 400 && height <= 400) {
	                byte[] arrayBytes = event.getFile().getContents();
	                BASE64Encoder eB = new BASE64Encoder();
	                String photo = eB.encodeBuffer(arrayBytes);
	                getNuevoDoc().setFoto(photo);
	                setNombreArchivoFotoDoc(event.getFile().getFileName());
	            } else {
	                FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La foto del doctor debe de ser de 400px de ancho por 400px de alto.", ""));
	            }
	        } catch (IOException e) {
	            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
	        }
	    }
	    
	    public void uploadImagenEditDoc(FileUploadEvent event) throws Exception {
	        try {
	            byte[] image = event.getFile().getContents();
	            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
	            int width = bi.getWidth();
	            int height = bi.getHeight();
	            if (width <= 400 && height <= 400) {
	                byte[] arrayBytes = event.getFile().getContents();
	                BASE64Encoder eB = new BASE64Encoder();
	                String photo = eB.encodeBuffer(arrayBytes);
	                getDoctorSelectedEdicion().setFoto(photo);
	                setNombreArchivoFotoDoc(event.getFile().getFileName());
	            } else {
	                FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La foto del doctor debe de ser de 400px de ancho por 400px de alto.", ""));
	            }
	        } catch (IOException e) {
	            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
	        }
	    }
	    
	
	 public void resetearForm() throws Exception {
	        setUsuario(new Usuario());
	        setNuevoDoc(new Doctor());
	        setDoctorSelectedEdicion(new Doctor());
	        setSelectedDepartamento(new SelectStringValue());
	        setSelectedEspecialidad(new SelectStringValue());
	        setNombreArchivoFotoDoc(null);
	        setSelectedSexo(new SelectStringString());
	        setPass1(null);
	        setPass2(null);
	    }

	public ArrayList<Doctor> getAdminDoctoresList() {
		return adminDoctoresList;
	}

	public void setAdminDoctoresList(ArrayList<Doctor> adminDoctoresList) {
		this.adminDoctoresList = adminDoctoresList;
	}

	public Doctor getDoctorSelectedEdicion() {
		return doctorSelectedEdicion;
	}

	public void setDoctorSelectedEdicion(Doctor doctorSelectedEdicion) {
		this.doctorSelectedEdicion = doctorSelectedEdicion;
	}

	public String getNombreArchivoFotoDoc() {
		return nombreArchivoFotoDoc;
	}

	public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
		this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
	}

	public Doctor getNuevoDoc() {
		return nuevoDoc;
	}

	public void setNuevoDoc(Doctor nuevoDoc) {
		this.nuevoDoc = nuevoDoc;
	}

	public SelectStringValue getSelectedDepartamento() {
		return selectedDepartamento;
	}

	public void setSelectedDepartamento(SelectStringValue selectedDepartamento) {
		this.selectedDepartamento = selectedDepartamento;
	}

	public SelectStringValue getSelectedEspecialidad() {
		return selectedEspecialidad;
	}

	public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
		this.selectedEspecialidad = selectedEspecialidad;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SelectStringString getSelectedSexo() {
		return selectedSexo;
	}

	public void setSelectedSexo(SelectStringString selectedSexo) {
		this.selectedSexo = selectedSexo;
	}

	public ArrayList<SelectStringString> getSexoList() {
		return sexoList;
	}

	public void setSexoList(ArrayList<SelectStringString> sexoList) {
		this.sexoList = sexoList;
	}

	public ArrayList<SelectStringValue> getDepartamentoList() {
		return departamentoList;
	}

	public void setDepartamentoList(ArrayList<SelectStringValue> departamentoList) {
		this.departamentoList = departamentoList;
	}

	public ArrayList<SelectStringValue> getEspecialidadesList() {
		return especialidadesList;
	}

	public void setEspecialidadesList(ArrayList<SelectStringValue> especialidadesList) {
		this.especialidadesList = especialidadesList;
	}

}
