package com.shop.validators.user;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.shop.model.dao.UserDAO;
import com.shop.model.dto.User;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator {

	public EmailValidator() {
		
	}

	@Override
	public void validate(FacesContext fc, UIComponent ui, Object value) throws ValidatorException {
		
		String email = (String)value;
		
		if(email.length() >= 5) {
			
			if(email.contains("@") == true) {
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getByEmail(email);
				
				if(user != null) {
					FacesMessage msg = new FacesMessage("E-mail jest ju¿ zajêty.");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(msg);
				}
			} else {
				FacesMessage msg = new FacesMessage("SprawdŸ czy prawid³owo poda³eœ e-mail.");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		} else {
			FacesMessage msg = new FacesMessage("E-mail jest zbyt krótki.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
