package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.CrearTurnosMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("consultaDoctorConverter")
public class ConsultaDoctorConverter implements Converter<Object> {
	
    public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
    	SelectStringValue doc = new SelectStringValue();
    	ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{crearTurnosMB}", CrearTurnosMB.class);
    	CrearTurnosMB crearTurnosMB = (CrearTurnosMB)vex.getValue(ctx.getELContext());
    	if (crearTurnosMB !=null && crearTurnosMB.getListDoctores() != null ) {
            boolean encontre = false;
            for (int i = 0; i < crearTurnosMB.getListDoctores().size() && !encontre; i++) {
                doc = crearTurnosMB.getListDoctores().get(i);
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
