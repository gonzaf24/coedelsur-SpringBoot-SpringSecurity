package com.coedelsur.bean.doctor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
import com.coedelsur.model.Diagnostico;
import com.coedelsur.model.Medicamento;
import com.coedelsur.model.Paciente;
import com.coedelsur.model.PacienteFile;
import com.coedelsur.service.AgendaServ;
import com.coedelsur.service.DoctorServ;
import com.coedelsur.service.HistoriaClinicaServ;
import com.coedelsur.service.PacienteServ;
import com.coedelsur.util.Base64;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import sun.misc.BASE64Encoder;

@SessionScope
@Component
public class HistoriaClinicaMB implements Serializable {

	@Inject
	DoctorServ doctorSrv;
	
	@Inject
	AgendaServ agendaServ;
	
	@Inject
	PacienteServ pacienteServ;
	
	@Inject
	HistoriaClinicaServ historiaClinicaServ;
	
	@Inject
	FichaPacienteMB fichaPacienteMB;
	
	@Inject
	DiagnosticoMB diagnosticoMB;
	
	@Inject
	SessionMB sessionMB;
	
    private static final long serialVersionUID = 1L;
    private Paciente paciente;
    private Integer selectedIdPaciente;
    private ConsultaMedica nuevaConsultaMedica = new ConsultaMedica();
    private Antecedente nuevoAntecedente = new Antecedente();
    private ArrayList<ConsultaMedica> consultasMedicasList = new ArrayList<ConsultaMedica>();
    private ArrayList<Antecedente> antecedentesList = new ArrayList<Antecedente>();
    private Antecedente selectedAntecedente = new Antecedente();
    private ConsultaMedica selectedConsultaMedica = new ConsultaMedica();
    private ArrayList<PacienteFile> listMisFiles;
    private transient StreamedContent archivo;
    private String nuevoDocumento;
    private String nombreArchivoFotoDoc;
    private PacienteFile pacienteFile = new PacienteFile();

    public HistoriaClinicaMB() {
    
    }
    
    public void init() throws Exception {
        setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
        setConsultasMedicasList(historiaClinicaServ.obtenerConsultasMedicasList(fichaPacienteMB.getSelectedIdPaciente()));
        setListMisFiles(pacienteServ.obtenerArchivosPaciente(fichaPacienteMB.getSelectedIdPaciente()));
    }
    
    public void eliminarAntecedente(String tipo , Antecedente antecedente) throws Exception {
        
        try {
            Integer indexAnt = getAntecedentesList().indexOf(antecedente);
            
            if(tipo.equals("personal")){
                historiaClinicaServ.eliminarAntecedentePersonal(antecedente.getPersonal());
                getAntecedentesList().get(indexAnt).setPersonal(null);
                
            }else if (tipo.equals("familiar")){
                historiaClinicaServ.eliminarAntecedenteFamiliar(antecedente.getFamiliar());
                getAntecedentesList().get(indexAnt).setFamiliar(null);
                
            }else if (tipo.equals("medicamento")){
                historiaClinicaServ.eliminarAntecedenteMedicamento(antecedente.getMedicamento());
                getAntecedentesList().get(indexAnt).setMedicamento(null);
                
            }else if (tipo.equals("antecedente")){
                historiaClinicaServ.eliminarAntecedente(antecedente);
                getAntecedentesList().remove(antecedente);
            }
            
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
          }   
    }
    
  
    public void eliminarConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
        try {
            historiaClinicaServ.eliminarConsultaMedica(consultaMedica);
            getConsultasMedicasList().remove(consultaMedica);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear antecedente: ", ""));
        }
    }
    
    public void selectAntecedente(Antecedente antecedente) throws Exception {
        try {
            setSelectedAntecedente(antecedente);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al selectAntecedente: ", ""));
          }   
    }
    
    public void selectConsultaMedica(ConsultaMedica consultaMedica) throws Exception {
        try {
            setSelectedConsultaMedica(consultaMedica);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al selectConsultaMedica: ", ""));
          }   
    }
    
    public void editarAntecedentePersonal() throws Exception {
        try {
            historiaClinicaServ.editarAntecedentePersonal(getSelectedAntecedente().getPersonal());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente antecedente personal", ""));
          } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al editar antecedente personal", ""));
          }   
    }
    
    public void editarAntecedenteFamiliar() throws Exception {
        try {
             historiaClinicaServ.editarAntecedenteFamiliar(getSelectedAntecedente().getFamiliar());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente antecedente familiar", ""));
          } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al editar antecedente familiar", ""));
          }    
    }
    
    
    public void editarAntecedenteMedicamento() throws Exception {
        try {
            historiaClinicaServ.editarAntecedenteMedicamento(getSelectedAntecedente().getMedicamento());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente antecedente medicamento", ""));
          } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Error al editar antecedente medicamento", ""));
          }    
    }
    
    public void editarConsultaMedica() throws Exception {
        try {
            historiaClinicaServ.editarConsultaMedica(getSelectedConsultaMedica());
            setConsultasMedicasList(historiaClinicaServ.obtenerConsultasMedicasList(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamente la consulta", ""));
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al editarConsultaMedica: ", ""));
          }   
    }
    
    
    public void borrarDiagnostico() throws Exception {
        try {
            getNuevaConsultaMedica().setDiagnostico("");
            getNuevaConsultaMedica().setDiagnosticoObject(null);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al borrarDiagnostico: ", ""));
          }   
    }
    
    public void borrarDiagnosticoEditar() throws Exception {
        try {
            getSelectedConsultaMedica().setDiagnostico("");
            getSelectedConsultaMedica().setDiagnosticoObject(null);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al borrarDiagnostico: ", ""));
          }   
    }
    
    public void confirmarDiagnostico() throws Exception {
        try {
            getNuevaConsultaMedica().setDiagnostico(diagnosticoMB.getSelectedCIE10().getLabel());
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setIdDoctor(sessionMB.getDoctor().getId());
            diagnostico.setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            diagnostico.setIdConsulta(getNuevaConsultaMedica().getId());
            diagnostico.setDescripcion(diagnosticoMB.getSelectedCIE10().getLabel());
            if(diagnosticoMB.getSelectedCIE10().getId10().equalsIgnoreCase("0000")){
                diagnostico.setTipo("LIBRE");
            }else{
                diagnostico.setTipo("CIE10");
            }
            diagnostico.setId10(diagnosticoMB.getSelectedCIE10().getId10());
            diagnostico.setDec10(diagnosticoMB.getSelectedCIE10().getDec10());
            diagnostico.setGrp10(diagnosticoMB.getSelectedCIE10().getGrp10());
            getNuevaConsultaMedica().setDiagnosticoObject(diagnostico);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al confirmarDiagnosticoAConsultaMedica: ", ""));
          }   
    }
    
    
    public void confirmarDiagnosticoEditar() throws Exception {
        try {
            getSelectedConsultaMedica().setDiagnostico(diagnosticoMB.getSelectedCIE10().getLabel());
            Diagnostico diagnostico = new Diagnostico();
            diagnostico.setIdDoctor(sessionMB.getDoctor().getId());
            diagnostico.setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            diagnostico.setIdConsulta(getSelectedConsultaMedica().getId());
            diagnostico.setDescripcion(diagnosticoMB.getSelectedCIE10().getLabel());
            if(diagnosticoMB.getSelectedCIE10().getId10().equalsIgnoreCase("0000")){
                diagnostico.setTipo("LIBRE");
            }else{
                diagnostico.setTipo("CIE10");
            }
            diagnostico.setId10(diagnosticoMB.getSelectedCIE10().getId10());
            diagnostico.setDec10(diagnosticoMB.getSelectedCIE10().getDec10());
            diagnostico.setGrp10(diagnosticoMB.getSelectedCIE10().getGrp10());
            getSelectedConsultaMedica().setDiagnosticoObject(diagnostico);
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al confirmarDiagnosticoAConsultaMedica: ", ""));
          }   
    }
    
    
    
    
    public void guardarConsultaMedica() throws Exception {
        try {
            getNuevaConsultaMedica().setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            getNuevaConsultaMedica().setIdDoctor(sessionMB.getDoctor().getId());
            historiaClinicaServ.nuevaConsultaMedica(getNuevaConsultaMedica());
            setConsultasMedicasList(historiaClinicaServ.obtenerConsultasMedicasList(fichaPacienteMB.getSelectedIdPaciente()));
            limpiarConsultaMedicaForm();
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_INFO, "Se a guardado correctamente la nueva consulta medica", ""));
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardarConsultaMedica: ", ""));
          }   
    }
    
    public void nuevoAntecedente() throws Exception {
        try {
            setNuevoAntecedente(new Antecedente());
            getNuevoAntecedente().setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al nuevo antecedente: ", e.getMessage()));
          }   
    }
    
    public void nuevoDocumento() throws Exception {
        try {
            setNombreArchivoFotoDoc(null);
            setNuevoDocumento(null);
            setPacienteFile(new PacienteFile());
          } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al nuevo documento: ", ""));
          }   
    }
    
    
    public void guardarAntecedente() throws Exception {
        try {
            historiaClinicaServ.nuevoAntecedente(getNuevoAntecedente());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            eliminarAntecedenteForm("todos");
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha guardado correctamente el antecedente", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente: " + e.getMessage(), ""));
        }
    }
    
    public void guardarAntecedentePersonal() throws Exception {
        try {
            getNuevoAntecedente().setId(getSelectedAntecedente().getId());
            getNuevoAntecedente().setIdPaciente(getSelectedAntecedente().getIdPaciente());
            getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
            historiaClinicaServ.nuevoAntecedentePersonal(getNuevoAntecedente());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            eliminarAntecedenteForm("todos");
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado el antecedente personal", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente personal: " + e.getMessage(), ""));
        }
    }
    
    public void guardarAntecedenteFamiliar() throws Exception {
        try {
            getNuevoAntecedente().setId(getSelectedAntecedente().getId());
            getNuevoAntecedente().setIdPaciente(getSelectedAntecedente().getIdPaciente());
            getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
            historiaClinicaServ.nuevoAntecedenteFamiliar(getNuevoAntecedente());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            eliminarAntecedenteForm("todos");
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado el antecedente familiar", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente familiar: " + e.getMessage(), ""));
        }
    }
    
    public void guardarAntecedenteMedicamento() throws Exception {
        try {
            getNuevoAntecedente().setId(getSelectedAntecedente().getId());
            getNuevoAntecedente().setIdPaciente(getSelectedAntecedente().getIdPaciente());
            getNuevoAntecedente().setIdDoctor(sessionMB.getDoctor().getId());
            historiaClinicaServ.nuevoAntecedenteMedicamento(getNuevoAntecedente());
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            eliminarAntecedenteForm("todos");
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado el antecedente medicamento", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar antecedente medicamento: " + e.getMessage(), ""));
        }
    }
    
    public void eliminarAntecedenteForm(String antecedente) throws Exception {
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
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar antecedente: ", ""));
          }   
    }
    
    
    public void limpiarConsultaMedicaForm() throws Exception {
        setNuevaConsultaMedica(new ConsultaMedica());
    }
    
    public void cancelarDocumento() throws Exception {
        setNuevoDocumento(null);
        setPacienteFile(new PacienteFile());
    }

    
    public void uploadDocPaciente(FileUploadEvent event) throws Exception {
        try {
            byte[] image = event.getFile().getContents();
                BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
                byte[] arrayBytes = event.getFile().getContents();
                BASE64Encoder eB = new BASE64Encoder();
                String documento = eB.encodeBuffer(arrayBytes);
                setNuevoDocumento(documento);
                setNombreArchivoFotoDoc(event.getFile().getFileName());
       
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica",new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir la foto : " + e.getMessage(), ""));
        }
    }
    
    public void obtenerArchivo(Integer idArchivo) throws Exception {
        Archivo archivo = pacienteServ.obtenerArchivo(idArchivo);
        byte[] base64DecodedByteArray = Base64.decode(archivo.getDocumento());
        InputStream is = new ByteArrayInputStream(base64DecodedByteArray);
        SimpleDateFormat formatoNombreDoc = new SimpleDateFormat("dd-MM-yy:HH:mm:SS");
        String nombreDoc = new String(formatoNombreDoc.format(new Date()));
        if (archivo.getTipo().equalsIgnoreCase("JPG")) {
            setArchivo(new DefaultStreamedContent(is, "image/jpg", nombreDoc+"_JPG_doc_coedelsur.jpg"));
        } else if (archivo.getTipo().equalsIgnoreCase("PNG")) {
            setArchivo(new DefaultStreamedContent(is, "image/png", nombreDoc+"_PNG_doc_coedelsur.png"));
        } else if (archivo.getTipo().equalsIgnoreCase("XLS")) {
            setArchivo(new DefaultStreamedContent(is, "application/xls", nombreDoc+"_XLS_doc_coedelsur.xls"));
        } else if (archivo.getTipo().equalsIgnoreCase("DOC")) {
            setArchivo(new DefaultStreamedContent(is, "application/doc", nombreDoc+"_DOC_doc_coedelsur.doc"));
        } else if (archivo.getTipo().equalsIgnoreCase("PDF")) {
            setArchivo(new DefaultStreamedContent(is, "application/pdf", nombreDoc+"_PDF_doc_coedelsur.pdf"));
        }
    }
    
    
    public void confirmarNuevoDocumentoPaciente() throws Exception {
        try {
            getPacienteFile().setIdPaciente(fichaPacienteMB.getSelectedIdPaciente());
            getPacienteFile().setIdDoctor(sessionMB.getDoctor().getId());
            Archivo a = new Archivo();
            a.setDocumento(getNuevoDocumento());
            getPacienteFile().setArchivo(a);
            pacienteServ.nuevoArchivoPaciente(getPacienteFile());
            setListMisFiles(pacienteServ.obtenerArchivosPaciente(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha adjuntado correctamente el documento.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al adjuntar el documento :" + e.getMessage(), ""));
        }
    }
    
    public void eliminarDocumento(PacienteFile pacienteFile) throws Exception {
        try {
            pacienteServ.eliminarDocumento(pacienteFile);
            setListMisFiles(pacienteServ.obtenerArchivosPaciente(fichaPacienteMB.getSelectedIdPaciente()));
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamente el documento.", ""));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al eliminar el documento :" + e.getMessage(), ""));
        }
    }


    public void imprimir() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("stationery.pdf");
        
        PdfReader reader = new PdfReader(input);
        
        Rectangle pagesize = reader.getPageSize(1);
        PdfStamper stamper = new PdfStamper(reader, baos);
        Paragraph pHeaderDate = new Paragraph();
        
        SimpleDateFormat formatoFechaPDF = new SimpleDateFormat("d MMMM , yyyy", new Locale("es", "ES"));
        String fechaPDF = new String(formatoFechaPDF.format(new Date()));
        
        pHeaderDate.add(new Chunk(fechaPDF, new Font(FontFamily.HELVETICA, 8)));
        Paragraph pHeaderInfo = new Paragraph();
        pHeaderInfo.add(new Chunk("Tel: (598) 2902 56 76 Dirección: Torre de los Profesionales Yaguarón 1407, esquina Colonia. Apto. 1505 Montevideo - Uruguay", new Font(FontFamily.HELVETICA, 8)));
        Paragraph pFooter = new Paragraph();
        pFooter.add(new Chunk("Clínica COEdelSUR "));
        Paragraph p = new Paragraph();
        p.add(new Chunk("Aca te listo absolutamente toda la indformacion que quieras "));
        p.add(new Chunk("Pero tenes que decidir vos el diseño y la informacion a mostrar", new Font(FontFamily.HELVETICA, 12, Font.BOLD)));
        AcroFields form = stamper.getAcroFields();
        Rectangle recHeaderInfo = form.getFieldPositions("headerInfo").get(0).position;
        Rectangle recHeaderDate = form.getFieldPositions("headerDate").get(0).position;
        Rectangle rectFooter = form.getFieldPositions("footer").get(0).position;
        Rectangle rect = form.getFieldPositions("body").get(0).position;
        int status;
        PdfImportedPage newPage = null;
        
        ColumnText column = new ColumnText(stamper.getOverContent(1));
        column.setSimpleColumn(rect);
        
        ColumnText columnFooter = new ColumnText(stamper.getOverContent(1));
        columnFooter.setSimpleColumn(rectFooter);
        
        ColumnText columnHeadeDate = new ColumnText(stamper.getOverContent(1));
        columnHeadeDate.setSimpleColumn(recHeaderDate);
        
        ColumnText columnHeadeInfo = new ColumnText(stamper.getOverContent(1));
        columnHeadeInfo.setSimpleColumn(recHeaderInfo);
        
        int pagecount = 1;
        
        for (int i = 0; i <= 3; i++) {
            
            column.addElement(new Paragraph("Pagina: " + i));
            column.addElement(p);
            status = column.go();
            columnHeadeDate.addElement(pHeaderDate);
            status = columnHeadeDate.go();
            columnHeadeInfo.addElement(pHeaderInfo);
            status = columnHeadeInfo.go();
            columnFooter.addElement(pFooter);
            status = columnFooter.go();
            
            newPage = loadPage(newPage, reader, stamper);
            
            stamper.insertPage(++pagecount, pagesize);
            PdfContentByte canvas = stamper.getOverContent(pagecount);
            canvas.addTemplate(newPage, 0, 0);
            columnHeadeDate.setCanvas(canvas);
            columnHeadeDate.setSimpleColumn(recHeaderDate);
            columnHeadeDate.go();
            columnHeadeInfo.setCanvas(canvas);
            columnHeadeInfo.setSimpleColumn(recHeaderInfo);
            columnHeadeInfo.go();
            column.setCanvas(canvas);
            column.setSimpleColumn(rect);
            column.go();
            columnFooter.setCanvas(canvas);
            columnFooter.setSimpleColumn(rectFooter);
            columnFooter.go();
            
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
        
        SimpleDateFormat formatoNombreDoc = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String nombreDoc = new String(formatoNombreDoc.format(new Date()));
        InputStream stream = new ByteArrayInputStream(baos.toByteArray());
        setArchivo(new DefaultStreamedContent(stream, "application/pdf", nombreDoc+"_PDF_HCLINICA_coedelsur.pdf"));
    }
    
    public PdfImportedPage loadPage(PdfImportedPage page, PdfReader reader, PdfStamper stamper) {
        if (page == null) {
            return stamper.getImportedPage(reader, 1);
        }
        return page;
    }
 
    public void triggerNewPage(PdfStamper stamper, Rectangle pagesize, PdfImportedPage page, ColumnText column, ColumnText columnFooter, ColumnText columnHeadeDate, ColumnText columnHeadeInfo,  Rectangle rect, Rectangle rectFooter,  Rectangle recHeaderDate, Rectangle recHeaderInfo, int pagecount) throws DocumentException {
        stamper.insertPage(pagecount, pagesize);
        PdfContentByte canvas = stamper.getOverContent(pagecount);
        canvas.addTemplate(page, 0, 0);
        columnHeadeDate.setCanvas(canvas);
        columnHeadeDate.setSimpleColumn(recHeaderDate);
        columnHeadeDate.go();
        columnHeadeInfo.setCanvas(canvas);
        columnHeadeInfo.setSimpleColumn(recHeaderInfo);
        columnHeadeInfo.go();
        column.setCanvas(canvas);
        column.setSimpleColumn(rect);
        column.go();
        columnFooter.setCanvas(canvas);
        columnFooter.setSimpleColumn(rectFooter);
        columnFooter.go();
    }
    
    
    class MyFooter extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 6, Font.ITALIC);
            
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase header = new Phrase("Clínica COEdelSUR", ffont);
            Phrase footer = new Phrase("Clínica COEdelSUR", ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.top() + 10, 0);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        }
    }
    
    public void nuevoImprimir() throws Exception {
        try {
            setAntecedentesList(historiaClinicaServ.obtenerAntecedentesList(fichaPacienteMB.getSelectedIdPaciente()));
            setConsultasMedicasList(historiaClinicaServ.obtenerConsultasMedicasList(fichaPacienteMB.getSelectedIdPaciente()));
            setListMisFiles(pacienteServ.obtenerArchivosPaciente(fichaPacienteMB.getSelectedIdPaciente()));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error en nuevo imprimir :" + e.getMessage(), ""));
        }
    }
    
    
    
    public void selectCheckImprimir(Antecedente antecedente , String tipo) throws Exception {
        try {
            int index = getAntecedentesList().indexOf(antecedente);
            Antecedente antecedenteAux = getAntecedentesList().get(index);
            if(tipo.equalsIgnoreCase("personal")){
                if(!antecedente.getPersonal().isImprimir()){
                    antecedenteAux.getPersonal().setImprimir(true);
                }else{
                    antecedenteAux.getPersonal().setImprimir(false);
                }
                
            }else if (tipo.equalsIgnoreCase("familiar")){
                if(!antecedente.getFamiliar().isImprimir()){
                    antecedenteAux.getFamiliar().setImprimir(true);
                }else{
                    antecedenteAux.getFamiliar().setImprimir(false);
                }
                
            }else if (tipo.equalsIgnoreCase("medicamento")){
                if(!antecedente.getMedicamento().isImprimir()){
                    antecedenteAux.getMedicamento().setImprimir(true);
                }else{
                    antecedenteAux.getMedicamento().setImprimir(false);
                }
               
            }else if(tipo.equalsIgnoreCase("todos")){
                if(!antecedente.isImprimir()){
                    antecedenteAux.setImprimir(true);
                }else{
                    antecedenteAux.setImprimir(false);
                }
            }
           
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al realizar el check :" + e.getMessage(), ""));
        }
    }
    
    public void selectCheckImprimir(ConsultaMedica consultaMedica) throws Exception {
        try {
            int index = getConsultasMedicasList().indexOf(consultaMedica);
            ConsultaMedica consultaMedicaAux = getConsultasMedicasList().get(index);
                if(!consultaMedica.isImprimir()){
                    consultaMedicaAux.setImprimir(true);
                }else{
                    consultaMedicaAux.setImprimir(false);
                }
         
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesHistoriaClinica", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo error al realizar el check :" + e.getMessage(), ""));
        }
    }
    
    public PacienteFile getPacienteFile() {
        return pacienteFile;
    }

    public void setPacienteFile(PacienteFile pacienteFile) {
        this.pacienteFile = pacienteFile;
    }

    public String getNombreArchivoFotoDoc() {
        return nombreArchivoFotoDoc;
    }

    public void setNombreArchivoFotoDoc(String nombreArchivoFotoDoc) {
        this.nombreArchivoFotoDoc = nombreArchivoFotoDoc;
    }

    public String getNuevoDocumento() {
        return nuevoDocumento;
    }

    public void setNuevoDocumento(String nuevoDocumento) {
        this.nuevoDocumento = nuevoDocumento;
    }

    public StreamedContent getArchivo() {
        return archivo;
    }

    public void setArchivo(StreamedContent archivo) {
        this.archivo = archivo;
    }

    public ArrayList<PacienteFile> getListMisFiles() {
        return listMisFiles;
    }

    public void setListMisFiles(ArrayList<PacienteFile> listMisFiles) {
        this.listMisFiles = listMisFiles;
    }

    public ConsultaMedica getSelectedConsultaMedica() {
        return selectedConsultaMedica;
    }

    public void setSelectedConsultaMedica(ConsultaMedica selectedConsultaMedica) {
        this.selectedConsultaMedica = selectedConsultaMedica;
    }

    public Antecedente getSelectedAntecedente() {
        return selectedAntecedente;
    }

    public void setSelectedAntecedente(Antecedente selectedAntecedente) {
        this.selectedAntecedente = selectedAntecedente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getSelectedIdPaciente() {
        return selectedIdPaciente;
    }

    public void setSelectedIdPaciente(Integer selectedIdPaciente) {
        this.selectedIdPaciente = selectedIdPaciente;
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

    public ArrayList<ConsultaMedica> getConsultasMedicasList() {
        return consultasMedicasList;
    }

    public void setConsultasMedicasList(ArrayList<ConsultaMedica> consultasMedicasList) {
        this.consultasMedicasList = consultasMedicasList;
    }

    public ArrayList<Antecedente> getAntecedentesList() {
        return antecedentesList;
    }

    public void setAntecedentesList(ArrayList<Antecedente> antecedentesList) {
        this.antecedentesList = antecedentesList;
    }
}
