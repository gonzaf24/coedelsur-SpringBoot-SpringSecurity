package com.coedelsur.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.NewUserMB;
import com.coedelsur.model.Doctor;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("departamentoRegistroConverter")
public class DepartamentoRegistroConverter implements Converter<Object> {
	
	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		if(value.equalsIgnoreCase(new String("Departamento"))){
            return  new Doctor();
        }
		SelectStringValue doc = new SelectStringValue();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),"#{newUserMB}", NewUserMB.class);
		NewUserMB newUserMB = (NewUserMB) vex.getValue(ctx.getELContext());
		if (newUserMB != null && newUserMB.getDepartamentosList() != null) {
			boolean encontre = false;
			for (int i = 0; i < newUserMB.getDepartamentosList().size() && !encontre; i++) {
				doc = newUserMB.getDepartamentosList().get(i);
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
