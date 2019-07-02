package com.coedelsur.bean.doctor;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.SelectStringValue;
import com.coedelsur.service.ComboUtilsServ;
import com.coedelsur.service.ConsultaServ;
import com.coedelsur.service.DoctorServ;

@SessionScope
@Component
public class AdministrarCodiguerasMB implements Serializable {

	@Inject
	ComboUtilsServ comboUtilsServ;
	@Inject
	DoctorServ doctorSrv;
	@Inject
	ConsultaServ consultaServ;
	@Inject
	SessionMB sessionMB;

	private static final long serialVersionUID = 1L;
	private ArrayList<SelectStringValue> tipoConsultaList;
	private ArrayList<SelectStringValue> especialidadesList;
	private ArrayList<SelectStringValue> consultoriosList;
	private DualListModel<SelectStringValue> especialidades;
	private DualListModel<SelectStringValue> tipoConsultas;
	private DualListModel<SelectStringValue> consultorios;
	private String nombreARegistrar;

	public AdministrarCodiguerasMB() {
	}

	public void init() {

		ArrayList<SelectStringValue> listaCompletaEsp = comboUtilsServ.obtenerEspecialdadesTodas();
		ArrayList<SelectStringValue> listaHabilitadosEsp = new ArrayList<SelectStringValue>();
		ArrayList<SelectStringValue> listaNoHabilitadosEsp = new ArrayList<SelectStringValue>();
		for (int i = 0; i < listaCompletaEsp.size(); i++) {
			SelectStringValue aux = listaCompletaEsp.get(i);
			if (aux.getActivo()) {
				listaHabilitadosEsp.add(aux);
			} else {
				listaNoHabilitadosEsp.add(aux);
			}
		}
		setEspecialidades(new DualListModel<SelectStringValue>(listaHabilitadosEsp, listaNoHabilitadosEsp));

		ArrayList<SelectStringValue> listaCompletaTC = comboUtilsServ.obtenerTipoConsultasTodas();
		ArrayList<SelectStringValue> listaHabilitadosTC = new ArrayList<SelectStringValue>();
		ArrayList<SelectStringValue> listaNoHabilitadosTC = new ArrayList<SelectStringValue>();
		for (int i = 0; i < listaCompletaTC.size(); i++) {
			SelectStringValue aux = listaCompletaTC.get(i);
			if (aux.getActivo()) {
				listaHabilitadosTC.add(aux);
			} else {
				listaNoHabilitadosTC.add(aux);
			}
		}
		setTipoConsultas(new DualListModel<SelectStringValue>(listaHabilitadosTC, listaNoHabilitadosTC));

		ArrayList<SelectStringValue> listaCompletaConsultorios = comboUtilsServ.obtenerConsultoriosTodos();
		ArrayList<SelectStringValue> listaHabilitadosConsultorios = new ArrayList<SelectStringValue>();
		ArrayList<SelectStringValue> listaNoHabilitadosConsultorios = new ArrayList<SelectStringValue>();
		for (int i = 0; i < listaCompletaConsultorios.size(); i++) {
			SelectStringValue aux = listaCompletaConsultorios.get(i);
			if (aux.getActivo()) {
				listaHabilitadosConsultorios.add(aux);
			} else {
				listaNoHabilitadosConsultorios.add(aux);
			}
		}
		setConsultorios(
				new DualListModel<SelectStringValue>(listaHabilitadosConsultorios, listaNoHabilitadosConsultorios));

	}

	public void clearMessages() throws Exception {

		FacesContext.getCurrentInstance().getMessageList();

		setNombreARegistrar(null);
	}

	public void crearCodigo(String tipo) throws Exception {
		try {

			if (tipo.equalsIgnoreCase(new String("esp"))) {
				comboUtilsServ.crearCodigoEspecialidad(getNombreARegistrar());
			} else if (tipo.equalsIgnoreCase(new String("tc"))) {
				comboUtilsServ.crearCodigoTipoConsulta(getNombreARegistrar());
			} else if (tipo.equalsIgnoreCase(new String("cons"))) {
				comboUtilsServ.crearCodigoConsultorio(getNombreARegistrar());
			}
			init();
			setNombreARegistrar(null);

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}

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

	public DualListModel<SelectStringValue> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(DualListModel<SelectStringValue> especialidades) {
		this.especialidades = especialidades;
	}

	public DualListModel<SelectStringValue> getTipoConsultas() {
		return tipoConsultas;
	}

	public void setTipoConsultas(DualListModel<SelectStringValue> tipoConsultas) {
		this.tipoConsultas = tipoConsultas;
	}

	public ArrayList<SelectStringValue> getConsultoriosList() {
		return consultoriosList;
	}

	public void setConsultoriosList(ArrayList<SelectStringValue> consultoriosList) {
		this.consultoriosList = consultoriosList;
	}

	public DualListModel<SelectStringValue> getConsultorios() {
		return consultorios;
	}

	public void setConsultorios(DualListModel<SelectStringValue> consultorios) {
		this.consultorios = consultorios;
	}

	public String getNombreARegistrar() {
		return nombreARegistrar;
	}

	public void setNombreARegistrar(String nombreARegistrar) {
		this.nombreARegistrar = nombreARegistrar;
	}
}
