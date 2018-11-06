package com.rekonsiliasi.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rekonsiliasi.model.UserRole;


public class RoleValidator implements Validator {

	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return UserRole.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "roleName.required");
	}
}