package com.coedelsur.bean.paciente;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.bean.doctor.FichaPacienteMB;
import com.coedelsur.model.Antecedente;
import com.coedelsur.model.AntecedenteObject;
import com.coedelsur.model.Archivo;
import com.coedelsur.model.ConsultaMedica;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.HistoriaClinicaServ;
import com.coedelsur.service.PacienteServ;
import com.coedelsur.util.Base64;

import sun.misc.BASE64Encoder;

@SessionScope
@Component
public class PacienteMB {

	@Inject
    PacienteServ pacienteServ;
	@Inject
	ComboUtilsServ comboUtilsServ;
	@Inject
	HistoriaClinicaServ historiaClinicaServ;
	@Inject
	SessionMB sessionMB;
	@Inject
	FichaPacienteMB fichaPacienteMB;
	
	private Paciente paciente;

	@PostConstruct
    public void prePaciente() throws Exception {
        paciente = pacienteServ.obtenerPaciente(sessionMB.getPaciente().getId());
    }

    public void guardarConsultaMedica() throws Exception {
        try {
        	fichaPacienteMB.getNuevaConsultaMedica().setIdPaciente(fichaPacienteMB.getPaciente().getId());
        	fichaPacienteMB.getNuevaConsultaMedica().setIdDoctor(sessionMB.getDoctor().getId());
            historiaClinicaServ.nuevaConsultaMedica(fichaPacienteMB.getNuevaConsultaMedica());
            cancelarConsultaMedica();
            FacesContext.getCurrentInstance().addMessage("messagesNuevaConsultaMedica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se a guardado correctamente la nueva consulta medica", ""));
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesNuevaConsultaMedica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear consulta medica: ", ""));
          }   
    }
    
    public void nuevoAntecedente() throws Exception {
        try {
        	fichaPacienteMB.setNuevoAntecedente(new Antecedente());
        	fichaPacienteMB.getNuevoAntecedente().setIdPaciente(fichaPacienteMB.getPaciente().getId());
        	fichaPacienteMB.getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
    }
    
    public void nuevoDocumento() throws Exception {
        try {
            fichaPacienteMB.setNombreArchivoFotoDoc(null);
            fichaPacienteMB.setFoto(null);
            fichaPacienteMB.setPacienteFile(new PacienteFile());
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
    }
    
    
    public void eliminarAntecedente(String antecedente) throws Exception {
        try {
            if(antecedente.equals("personal")){
                fichaPacienteMB.getNuevoAntecedente().setPersonal(new AntecedenteObject());
            }else if(antecedente.equals("familiar")){
                fichaPacienteMB.getNuevoAntecedente().setFamiliar(new AntecedenteObject());
            }else if(antecedente.equals("medicamento")){
               fichaPacienteMB.getNuevoAntecedente().setMedicamento(new Medicamento());
            }else if(antecedente.equals("todos")){
                fichaPacienteMB.getNuevoAntecedente().setPersonal(new AntecedenteObject());
                fichaPacienteMB.getNuevoAntecedente().setFamiliar(new AntecedenteObject());
                fichaPacienteMB.getNuevoAntecedente().setMedicamento(new Medicamento());
            }
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
    }
    
    public void guardarAntecedente() throws Exception {
        try {
            historiaClinicaServ.nuevoAntecedente(fichaPacienteMB.getNuevoAntecedente());
            eliminarAntecedente("todos");
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el antecedente", ""));
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesFichaPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente: " + e.getMessage(), ""));
          }   
    }
    
    
    public void cancelarConsultaMedica() throws Exception {
        fichaPacienteMB.setNuevaConsultaMedica(new ConsultaMedica());
    }
    
    public void cancelarDocumento() throws Exception {
        fichaPacienteMB.setNuevoDocumento(null);
        fichaPacienteMB.setPacienteFile(new PacienteFile());
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
                fichaPacienteMB.setFoto(photo);
                fichaPacienteMB.getPaciente().setFoto(photo);
                fichaPacienteMB.setNombreArchivoFotoDoc(event.getFile().getFileName());
            } else {
                FacesContext.getCurrentInstance().addMessage("messagesEditarPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La foto del paciente debe de ser de 400px de ancho por 400px de alto.", ""));
            }
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messagesEditarPaciente",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
        }
    }
 

    public void postBusquedaPaciente() {
        // TODO Auto-generated method stub
        
    }
    
    public void confirmarNuevoDocumentoPaciente() throws Exception {
        try {
            fichaPacienteMB.getPacienteFile().setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            fichaPacienteMB.getPacienteFile().setIdDoctor(sessionMB.getDoctor().getId());
            Archivo a = new Archivo();
            a.setDocumento(fichaPacienteMB.getNuevoDocumento());
            fichaPacienteMB.getPacienteFile().setArchivo(a);
            pacienteServ.nuevoArchivoPaciente(fichaPacienteMB.getPacienteFile());
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha adjuntado correctamente el documento.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al adjuntar el documento :" + e.getMessage(), ""));
        }
    }
    
    public void uploadDocPaciente(FileUploadEvent event) throws Exception {
        try {
            byte[] image = event.getFile().getContents();
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
                byte[] arrayBytes = event.getFile().getContents();
                BASE64Encoder eB = new BASE64Encoder();
                String documento = eB.encodeBuffer(arrayBytes);
                fichaPacienteMB.setNuevoDocumento(documento);
                fichaPacienteMB.getPaciente().setFoto(documento);
                fichaPacienteMB.setNombreArchivoFotoDoc(event.getFile().getFileName());
       
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messagesCrearDoctor",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
        }
    }
    
    public void confirmarEdicionPaciente() throws Exception {
        try {
            pacienteServ.editarDatosPaciente(fichaPacienteMB.getPaciente());
            FacesContext.getCurrentInstance().addMessage("messagesGeneralEdicion", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente los datos del paciente.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al guardar los datos del paciente :" + e.getMessage(), ""));
        }
        
    }
    
    public void obtenerArchivo(Integer idArchivo) throws Exception {
        Archivo archivo = pacienteServ.obtenerArchivo(idArchivo);
        byte[] base64DecodedByteArray = Base64.decode(archivo.getDocumento());
        InputStream is = new ByteArrayInputStream(base64DecodedByteArray);
        fichaPacienteMB.setArchivo(new DefaultStreamedContent(is, "image/png", "documentosClinico.png"));
    }

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
    
    
    
}
