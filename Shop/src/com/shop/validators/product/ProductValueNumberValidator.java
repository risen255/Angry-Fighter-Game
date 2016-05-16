package com.shop.validators.product;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@FacesValidator("ProductValueNumberValidator")
public class ProductValueNumberValidator implements Validator {

	public ProductValueNumberValidator() {
		
	}

	@Override
	public void validate(FacesContext fc, UIComponent ui, Object value) throws ValidatorException {
		
		int numberValue = (Integer)value;
		
		if(numberValue <= 0) {
			FacesMessage msg = new FacesMessage("Liczba powinna byæ wiêksza od zera.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
