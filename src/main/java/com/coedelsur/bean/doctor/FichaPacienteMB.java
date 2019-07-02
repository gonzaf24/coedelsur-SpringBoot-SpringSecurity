package com.coedelsur.bean.doctor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.Antecedente;
import com.coedelsur.model.AntecedenteObject;
import com.coedelsur.model.Archivo;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.HistoriaClinica;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.HistoriaClinicaServ;
import com.coedelsur.service.PacienteServ;
import com.coedelsur.util.Base64;

import sun.misc.BASE64Encoder;

@SessionScope
@Component
public class FichaPacienteMB implements Serializable {

	@Inject
	PacienteServ pacienteServ;

	@Inject
	HistoriaClinicaServ historiaClinicaServ;

	@Inject
	ComboUtilsServ comboUtilsServ;

	@Inject
	SessionMB sessionMB;

	@Inject
	BusquedaPacienteMB busquedaPacienteMB;

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private String email;
	private String foto;
	private String nombreArchivoFotoDoc;
	private SelectStringValue selectedDepartamento = new SelectStringValue();
	private HistoriaClinica historiaClinica;
	private ArrayList<HistoriaClinica> historiaClinicaList = new ArrayList<HistoriaClinica>();
	private Integer selectedIdPaciente;
	private ArrayList<PacienteFile> listMisFiles;
	private transient StreamedContent archivo;
	private String nuevoDocumento;
	private ConsultaMedica nuevaConsultaMedica = new ConsultaMedica();
	private Antecedente nuevoAntecedente = new Antecedente();
	private PacienteFile pacienteFile = new PacienteFile();
	private ArrayList<SelectStringValue> departamentoList;

	public FichaPacienteMB() {

	}

	public void init() throws Exception {
		try {
			departamentoList = sessionMB.getDepartamentoList();
			setPaciente(pacienteServ.obtenerPaciente(busquedaPacienteMB.getSelectedIdPaciente()));
			setHistoriaClinicaList(historiaClinicaServ.obtenerHistoriaClinica(getPaciente()));
			setListMisFiles(pacienteServ.obtenerArchivosPaciente(busquedaPacienteMB.getSelectedIdPaciente()));
			setSelectedDepartamento(getPaciente().getDepartamento());
			setNuevoAntecedente(new Antecedente());
			getNuevoAntecedente().setIdPaciente(getPaciente().getId());
			getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hubo error en el pre Ficha Paciente :" + e.getMessage(), ""));
		}
	}
	
	public void cancelarDocumento() throws Exception {
        setNuevoDocumento(null);
        setPacienteFile(new PacienteFile());
    }
	
	public void eliminarAntecedente(String antecedente) throws Exception {
        try {
            if(antecedente.equals("personal")){
                getNuevoAntecedente().setPersonal(new AntecedenteObject());
            }else if(antecedente.equals("familiar")){
                getNuevoAntecedente().setFamiliar(new AntecedenteObject());
            }else if(antecedente.equals("medicamento")){
               getNuevoAntecedente().setMedicamento(new Medicamento());
            }else if(antecedente.equals("todos")){
                getNuevoAntecedente().setPersonal(new AntecedenteObject());
                getNuevoAntecedente().setFamiliar(new AntecedenteObject());
                getNuevoAntecedente().setMedicamento(new Medicamento());
            }
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
	}
	
	public void guardarAntecedente() throws Exception {
        try {
            historiaClinicaServ.nuevoAntecedente(getNuevoAntecedente());
            eliminarAntecedente("todos");
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el antecedente", ""));
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente: " + e.getMessage(), ""));
          }   
    }
	
	public void nuevoAntecedente() throws Exception {
        try {
        	setNuevoAntecedente(new Antecedente());
        	getNuevoAntecedente().setIdPaciente(getPaciente().getId());
        	getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
    }
	    
	
	public void confirmarNuevoDocumentoPaciente() throws Exception {
        try {
            getPacienteFile().setIdPaciente(getSelectedIdPaciente());
            getPacienteFile().setIdDoctor(sessionMB.getDoctor().getId());
            Archivo a = new Archivo();
            a.setDocumento(getNuevoDocumento());
            getPacienteFile().setArchivo(a);
            pacienteServ.nuevoArchivoPaciente(getPacienteFile());
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha adjuntado correctamente el documento.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al adjuntar el documento :" + e.getMessage(), ""));
        }
    }
	
	public void confirmarEdicionPaciente() throws Exception {
        try {
        	paciente.setDepartamento(selectedDepartamento);
            pacienteServ.editarDatosPaciente(paciente);
            FacesContext.getCurrentInstance().addMessage("messagesGeneralEdicion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente los datos del paciente.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al guardar los datos del paciente :" + e.getMessage(), ""));
        }
        
    }
	
	public void uploadDocPaciente(FileUploadEvent event) throws Exception {
        try {
            byte[] image = event.getFile().getContents();
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
                byte[] arrayBytes = event.getFile().getContents();
                BASE64Encoder eB = new BASE64Encoder();
                String documento = eB.encodeBuffer(arrayBytes);
                setNuevoDocumento(documento);
                getPaciente().setFoto(documento);
                setNombreArchivoFotoDoc(event.getFile().getFileName());
       
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
        }
    }
	
	public void obtenerArchivo(Integer idArchivo) throws Exception {
        Archivo archivo = pacienteServ.obtenerArchivo(idArchivo);
        byte[] base64DecodedByteArray = Base64.decode(archivo.getDocumento());
        InputStream is = new ByteArrayInputStream(base64DecodedByteArray);
        setArchivo(new DefaultStreamedContent(is, "image/png", "documentosClinico.png"));
    }
	
	public void uploadImagenEditPaciente(FileUploadEvent event) throws Exception {
        try {
            byte[] image = event.getFile().getContents();
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
            int width = bi.getWidth();
            int height = bi.getHeight();
            if (width <= 400 && height <= 400) {
                byte[] arrayBytes = event.getFile().getContents();
                BASE64Encoder eB = new BASE64Encoder();
                String photo = eB.encodeBuffer(arrayBytes);
                setFoto(photo);
                getPaciente().setFoto(photo);
                setNombreArchivoFotoDoc(event.getFile().getFileName());
            } else {
                FacesContext.getCurrentInstance().addMessage("messagesEditarPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La foto del paciente debe de ser de 400px de ancho por 400px de alto.", ""));
            }
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messagesEditarPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
        }
    }

	public ArrayList<SelectStringValue> getDepartamentoList() {
		return departamentoList;
	}

	public void setDepartamentoList(ArrayList<SelectStringValue> departamentoList) {
		this.departamentoList = departamentoList;
	}


	public PacienteFile getPacienteFile() {
		return pacienteFile;
	}

	public void setPacienteFile(PacienteFile pacienteFile) {
		this.pacienteFile = pacienteFile;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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

	public String getNombreArchivoFotoDoc() {
		return nombreArchivoFotoDoc;
	}

	public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
		this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
	}

	public SelectStringValue getSelectedDepartamento() {
		return selectedDepartamento;
	}

	public void setSelectedDepartamento(SelectStringValue selectedDepartamento) {
		this.selectedDepartamento = selectedDepartamento;
	}

	public HistoriaClinica getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public ArrayList<HistoriaClinica> getHistoriaClinicaList() {
		return historiaClinicaList;
	}

	public void setHistoriaClinicaList(ArrayList<HistoriaClinica> historiaClinicaList) {
		this.historiaClinicaList = historiaClinicaList;
	}

	public Integer getSelectedIdPaciente() {
		return selectedIdPaciente;
	}

	public void setSelectedIdPaciente(Integer selectedIdPaciente) {
		this.selectedIdPaciente = selectedIdPaciente;
	}

	public ArrayList<PacienteFile> getListMisFiles() {
		return listMisFiles;
	}

	public void setListMisFiles(ArrayList<PacienteFile> listMisFiles) {
		this.listMisFiles = listMisFiles;
	}

	public StreamedContent getArchivo() {
		return archivo;
	}

	public void setArchivo(StreamedContent archivo) {
		this.archivo = archivo;
	}

	public String getNuevoDocumento() {
		return nuevoDocumento;
	}

	public void setNuevoDocumento(String nuevoDocumento) {
		this.nuevoDocumento = nuevoDocumento;
	}

	public ConsultaMedica getNuevaConsultaMedica() {
		return nuevaConsultaMedica;
	}

	public void setNuevaConsultaMedica(ConsultaMedica nuevaConsultaMedica) {
		this.nuevaConsultaMedica = nuevaConsultaMedica;
	}

	public Antecedente getNuevoAntecedente() {
		return nuevoAntecedente;
	}

	public void setNuevoAntecedente(Antecedente nuevoAntecedente) {
		this.nuevoAntecedente = nuevoAntecedente;
	}
}
