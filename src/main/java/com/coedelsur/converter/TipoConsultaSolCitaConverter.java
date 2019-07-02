package com.coedelsur.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.paciente.SolicitarCitaMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("tipoConsultaSolCitaConverter")
public class TipoConsultaSolCitaConverter implements Converter<Object> {
	
	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		if(value.equalsIgnoreCase(new String("Seleccionar"))){
            return  new SelectStringValue();
        }
		SelectStringValue doc = new SelectStringValue();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),	"#{solicitarCitaMB}", SolicitarCitaMB.class);
		SolicitarCitaMB solicitarCitaMB = (SolicitarCitaMB) vex.getValue(ctx.getELContext());
		if (solicitarCitaMB != null && solicitarCitaMB.getTipoConsultaList() != null) {
			boolean encontre = false;
			for (int i = 0; i < solicitarCitaMB.getTipoConsultaList().size() && !encontre; i++) {
				doc = solicitarCitaMB.getTipoConsultaList().get(i);
				if (doc.getCode().equals(Integer.valueOf(value))) {
					encontre = true;
					return doc;
				}
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((SelectStringValue) object).getCode());
		} else {
			return null;
		}
	}


    
}
