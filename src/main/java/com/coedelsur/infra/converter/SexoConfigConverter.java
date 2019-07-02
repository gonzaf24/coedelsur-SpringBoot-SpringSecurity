package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.AdministrarDoctoresMB;
import com.coedelsur.model.SelectStringString;

@FacesConverter("sexoConfigConverter")
public class SexoConfigConverter implements Converter<Object> {

	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		SelectStringString doc = new SelectStringString();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),	"#{administrarDoctoresMB}", AdministrarDoctoresMB.class);
		AdministrarDoctoresMB administrarDoctoresMB = (AdministrarDoctoresMB) vex.getValue(ctx.getELContext());
		if (administrarDoctoresMB != null && administrarDoctoresMB.getSexoList() != null) {
			boolean encontre = false;
			for (int i = 0; i < administrarDoctoresMB.getSexoList().size() && !encontre; i++) {
				doc = administrarDoctoresMB.getSexoList().get(i);
				if (doc.getCode().equals(value)) {
                    encontre = true;
                    return doc;
                }
			}
		}
		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((SelectStringString) object).getCode());
		} else {
			return null;
		}
	}
}
