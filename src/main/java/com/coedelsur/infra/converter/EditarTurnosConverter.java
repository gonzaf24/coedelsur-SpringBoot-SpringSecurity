package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.EditarTurnosMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("editarTurnosConverter")
public class EditarTurnosConverter implements Converter<Object> {
	
    public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
    	SelectStringValue doc = new SelectStringValue();
    	ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{editarTurnosMB}", EditarTurnosMB.class);
    	EditarTurnosMB editarTurnosMB = (EditarTurnosMB)vex.getValue(ctx.getELContext());
    	if (editarTurnosMB !=null && editarTurnosMB.getListDoctores() != null ) {
            boolean encontre = false;
            for (int i = 0; i < editarTurnosMB.getListDoctores().size() && !encontre; i++) {
                doc = editarTurnosMB.getListDoctores().get(i);
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
