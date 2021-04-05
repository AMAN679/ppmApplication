package com.aman.ppmapp.validator;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.aman.ppmapp.domain.User;

@Component
public class UserValidator implements Validator{

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		User user=(User) object;
		if(user.getPassword().length()<6)
		{
			errors.rejectValue("password","Length","Password must be atleast 6 characters");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword","Match","Password must Match");
				
		}	
		
	}

	
	

}
