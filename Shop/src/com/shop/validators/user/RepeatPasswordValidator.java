package com.shop.validators.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("RepeatPasswordValidator")
public class RepeatPasswordValidator implements Validator {

	public RepeatPasswordValidator() {
		
	}

	@Override
	public void validate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {	
		
		// Password
		UIInput passwordInput = (UIInput)fc.getViewRoot().findComponent("register:userPassword");
		String password;
		
		if(passwordInput.getSubmittedValue() != null) {
			password = (String)passwordInput.getSubmittedValue();
		} else {
			password = (String)passwordInput.getValue();
		}
		
		// Repeat password
		String repeatPassword = (String)value;
		
		// Check if passwords are same
		if(password.equals(repeatPassword) == false) {
			FacesMessage msg = new FacesMessage("Has³a siê nie zgadzaj¹.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
