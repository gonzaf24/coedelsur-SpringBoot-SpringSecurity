package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.ConsultaDoctor;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.ConsultaServ;
import com.coedelsur.service.DoctorServ;

@SessionScope
@Component
public class AdministrarConsultasMB implements Serializable {

	@Inject
	ComboUtilsServ comboUtilsServ;
	@Inject
	DoctorServ doctorSrv;
	@Inject
	ConsultaServ consultaServ;
	@Inject
	SessionMB sessionMB;

	private static final long serialVersionUID = 1L;
	private ArrayList<ConsultaDoctor> adminConsultasList = new ArrayList<ConsultaDoctor>();
	private SelectStringValue selectedEspecialidad = new SelectStringValue();
	private SelectStringValue selectedTipoConsulta = new SelectStringValue();
	private Integer precio;
	private String descripcion;

	private ArrayList<SelectStringValue> tipoConsultaList;
	private ArrayList<SelectStringValue> especialidadesList;

	public AdministrarConsultasMB() {
	}

	public void init() throws Exception {
		try {
			setAdminConsultasList(consultaServ.obtenerConsultas());
			setTipoConsultaList(comboUtilsServ.obtenerTipoConsultas());
			setEspecialidadesList(comboUtilsServ.obtenerEspecialdades());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesAdmConsultas", new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "Hubo error al obtener la lista de consultas :" + e.getMessage(), ""));
		}
	}

	public void confirmarConsulta() throws Exception {
		try {
			ConsultaDoctor consDoc = new ConsultaDoctor();
			consDoc.setEspecialidad(getSelectedEspecialidad());
			consDoc.setTipoConsulta(getSelectedTipoConsulta());
			consDoc.setDescripcion(getDescripcion());
			consDoc.setPrecio(getPrecio());
			if (validarExisteConsulta(consDoc)) {
				consultaServ.crearConsulta(consDoc);
				getAdminConsultasList().add(consDoc);

				Messages.create("").detail("Se ha creado correctamente la consulta.").add();
			} else {
				FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya se encuentra registrada una consulta con: "
								+ getSelectedTipoConsulta().getLabel() + " y " + getSelectedEspecialidad().getLabel(),
								""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("messagesEditarDoctor",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}

	}

	private boolean validarExisteConsulta(ConsultaDoctor consDoc) {
		for (int i = 0; i < getAdminConsultasList().size(); i++) {
			ConsultaDoctor consDocAux = getAdminConsultasList().get(i);
			if (consDocAux.getTipoConsulta().getCode().equals(consDoc.getTipoConsulta().getCode())
					&& consDocAux.getEspecialidad().getCode().equals(consDoc.getEspecialidad().getCode())) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<ConsultaDoctor> getAdminConsultasList() {
		return adminConsultasList;
	}

	public void setAdminConsultasList(ArrayList<ConsultaDoctor> adminConsultasList) {
		this.adminConsultasList = adminConsultasList;
	}

	public SelectStringValue getSelectedEspecialidad() {
		return selectedEspecialidad;
	}

	public void setSelectedEspecialidad(SelectStringValue selectedEspecialidad) {
		this.selectedEspecialidad = selectedEspecialidad;
	}

	public SelectStringValue getSelectedTipoConsulta() {
		return selectedTipoConsulta;
	}

	public void setSelectedTipoConsulta(SelectStringValue selectedTipoConsulta) {
		this.selectedTipoConsulta = selectedTipoConsulta;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<SelectStringValue> getTipoConsultaList() {
		return tipoConsultaList;
	}

	public void setTipoConsultaList(ArrayList<SelectStringValue> tipoConsultaList) {
		this.tipoConsultaList = tipoConsultaList;
	}

	public ArrayList<SelectStringValue> getEspecialidadesList() {
		return especialidadesList;
	}

	public void setEspecialidadesList(ArrayList<SelectStringValue> especialidadesList) {
		this.especialidadesList = especialidadesList;
	}

}
