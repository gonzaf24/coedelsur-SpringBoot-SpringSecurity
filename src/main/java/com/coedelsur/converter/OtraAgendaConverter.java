package com.coedelsur.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.OtraAgendaMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("otraAgendaConverter")
public class OtraAgendaConverter implements Converter<Object> {
	
    public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
    	SelectStringValue doc = new SelectStringValue();
    	ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{otraAgendaMB}", OtraAgendaMB.class);
    	OtraAgendaMB otraAgendaMB = (OtraAgendaMB)vex.getValue(ctx.getELContext());
    	if (otraAgendaMB !=null && otraAgendaMB.getListDoctores() != null ) {
            boolean encontre = false;
            for (int i = 0; i < otraAgendaMB.getListDoctores().size() && !encontre; i++) {
                doc = otraAgendaMB.getListDoctores().get(i);
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
