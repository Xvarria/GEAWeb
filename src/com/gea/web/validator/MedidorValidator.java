package com.gea.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gea.web.form.MedidorForm;
import com.gea.web.model.Medidor;
import com.gea.web.service.MedidorBO;

@Component
public class MedidorValidator implements Validator {

	//TODO remove this definition point to Constants values
	static final String FIELD_NAME = "fieldName";
	static final String ERROR_KEY = "error.key";
	static final String ERROR_KEY_VALIDATION = "error.key.validation";

	@Autowired
	private MedidorBO medidorBO;
	
	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		 return MedidorForm.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.validation.Validator\#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, FIELD_NAME, ERROR_KEY);
		
		MedidorForm command = (MedidorForm) target;
		Medidor medidor = command.getMedidor();
		if (StringUtils.isNotEmpty("")){
			try{
				errors.rejectValue(FIELD_NAME, ERROR_KEY);
			}catch (Exception e){
				errors.rejectValue(FIELD_NAME, ERROR_KEY_VALIDATION);
			}
		}
	}
	
	public void setErrorAtException(Errors errors, String messageKey){
		errors.rejectValue(FIELD_NAME, messageKey);
	}
}

