package com.rekonsiliasi.validator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rekonsiliasi.model.UserInfo;
import com.rekonsiliasi.model.UserRole;


public class UsersValidator implements Validator {

	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return UserInfo.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		UserInfo cand = (UserInfo) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "first_name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "last_name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job_title", "job_title.required");
		
		//if(!cand.getPassword().equals(cand.getPasswordConfirm())) {
		//	errors.rejectValue("passwordConfirm", "passwordConfirm.confirm");
		//}

	}
}