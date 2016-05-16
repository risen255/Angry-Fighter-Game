package com.shop.validators.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PhoneNumberValidator")
public class PhoneNumberValidator implements Validator {
	
	public PhoneNumberValidator() {
		
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String phoneNumber = (String)value;
		
		if(phoneNumber.length() < 9) {
			
			FacesMessage msg = new FacesMessage("Numer telefonu musi mieæ conajmniej 9 cyfr.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}