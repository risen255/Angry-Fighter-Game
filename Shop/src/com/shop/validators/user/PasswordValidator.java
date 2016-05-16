package com.shop.validators.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PasswordValidator")
public class PasswordValidator implements Validator {
	
	public PasswordValidator() {
		
	}

	@Override
	public void validate(FacesContext fc, UIComponent ui, Object value) throws ValidatorException {
		
		String password = (String)value;
		
		if(password.length() < 8) {
			FacesMessage msg = new FacesMessage("Has³o musi mieæ conajmniej 8 znaków.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
