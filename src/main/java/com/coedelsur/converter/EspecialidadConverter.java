package com.coedelsur.converter;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.coedelsur.bean.doctor.AdministrarConsultasMB;
import com.coedelsur.model.SelectStringValue;

@FacesConverter("especialidadesConverter")
public class EspecialidadConverter implements Converter<Object> {
	
	public Object getAsObject(FacesContext ctx, UIComponent uic, String value) {
		SelectStringValue doc = new SelectStringValue();
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),	"#{administrarConsultasMB}", AdministrarConsultasMB.class);
		AdministrarConsultasMB administrarConsultasMB = (AdministrarConsultasMB) vex.getValue(ctx.getELContext());
		if (administrarConsultasMB != null && administrarConsultasMB.getEspecialidadesList() != null) {
			boolean encontre = false;
			for (int i = 0; i < administrarConsultasMB.getEspecialidadesList().size() && !encontre; i++) {
				doc = administrarConsultasMB.getEspecialidadesList().get(i);
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
