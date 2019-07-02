package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.SessionMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("departamentoConfigConverter")
public class DepartamentoConfigConverter implements Converter<Object> {

	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		SelectStringValue doc = new SelectStringValue();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{sessionMB}", SessionMB.class);
		SessionMB sessionMB = (SessionMB) vex.getValue(ctx.getELContext());
		if (sessionMB != null && sessionMB.getDepartamentoList() != null) {
			boolean encontre = false;
			for (int i = 0; i < sessionMB.getDepartamentoList().size() && !encontre; i++) {
				doc = sessionMB.getDepartamentoList().get(i);
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
