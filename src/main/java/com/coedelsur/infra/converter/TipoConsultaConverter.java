package com.coedelsur.infra.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.AdministrarConsultasMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("tipoConsultaConverter")
public class TipoConsultaConverter implements Converter<Object> {
	
	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		SelectStringValue doc = new SelectStringValue();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),	"#{administrarConsultasMB}", AdministrarConsultasMB.class);
		AdministrarConsultasMB administrarConsultasMB = (AdministrarConsultasMB) vex.getValue(ctx.getELContext());
		if (administrarConsultasMB != null && administrarConsultasMB.getTipoConsultaList() != null) {
			boolean encontre = false;
			for (int i = 0; i < administrarConsultasMB.getTipoConsultaList().size() && !encontre; i++) {
				doc = administrarConsultasMB.getTipoConsultaList().get(i);
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
