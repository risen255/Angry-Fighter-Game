package com.shop.validators.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@FacesValidator("UsernameValidator")
public class UsernameValidator implements Validator {
	
	public UsernameValidator() {
		
	}

	@Override
	public void validate(FacesContext fc, UIComponent ui, Object value) throws ValidatorException {
		
		String username = (String)value;
		
		if(username.length() >= 5) {
			UserDAO userDAO = new UserDAO();
		
			User user = userDAO.getByUsername(username);
			
			if(user != null) {
				FacesMessage msg = new FacesMessage("Nazwa u¿ytkownika jest ju¿ zajêta.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage("Nazwa u¿ytkownika musi mieæ conajmniej 5 znaków.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
