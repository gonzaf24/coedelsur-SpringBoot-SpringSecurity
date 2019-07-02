package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.paciente.SolicitarCitaMB;
import com.coedelsur.model.AgendaTurno;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("agendaTurnoSolCitaCConverter")
public class AgendaTurnoSolCitaCConverter implements Converter<Object> {

	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		if (value.equalsIgnoreCase(new String("Seleccionar"))) {
			return new SelectStringValue();
		}
		AgendaTurno agTur = new AgendaTurno();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{solicitarCitaMB}", SolicitarCitaMB.class);
		SolicitarCitaMB solicitarCitaMB = (SolicitarCitaMB) vex.getValue(ctx.getELContext());
		if (solicitarCitaMB != null && solicitarCitaMB.getListTurnosAgendaDoctor() != null) {
			boolean encontre = false;
			for (int i = 0; i < solicitarCitaMB.getListTurnosAgendaDoctor().size() && !encontre; i++) {
				agTur = solicitarCitaMB.getListTurnosAgendaDoctor().get(i);
				if (agTur.getId().equals(Integer.valueOf(value))) {
					encontre = true;
					return agTur;
				}
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((AgendaTurno) object).getId());
		} else {
			return null;
		}
	}

}
