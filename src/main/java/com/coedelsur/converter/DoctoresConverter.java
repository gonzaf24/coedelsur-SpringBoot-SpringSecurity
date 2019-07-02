package com.coedelsur.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.paciente.SolicitarCitaMB;
import com.coedelsur.model.Doctor;

@FacesConverter("doctoresConverter")
public class DoctoresConverter implements Converter<Object>  {
	
	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		if(value.equalsIgnoreCase(new String("Seleccionar"))){
            return  new Doctor();
        }
		Doctor doc = new Doctor();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),	"#{solicitarCitaMB}", SolicitarCitaMB.class);
		SolicitarCitaMB solicitarCitaMB = (SolicitarCitaMB) vex.getValue(ctx.getELContext());
		if (solicitarCitaMB != null && solicitarCitaMB.getDoctoresList() != null) {
			boolean encontre = false;
			for (int i = 0; i < solicitarCitaMB.getDoctoresList().size() && !encontre; i++) {
				doc = solicitarCitaMB.getDoctoresList().get(i);
				if (doc.getId().equals(Integer.valueOf(value))) {
                    encontre = true;
                    return doc;
                }
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Doctor) object).getId());
		} else {
			return null;
		}
	}

    
}
