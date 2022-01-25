package chap03.spring.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterRequestValidator implements Validator{
	
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
			"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	
		RegisterRequest regReq = (RegisterRequest) target;
		
		// 이메일 검증
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		} else {
			Matcher matcher = Pattern.compile(emailRegExp).matcher(regReq.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}
		
		// 이름 검증
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		if(regReq.getName() != null && !regReq.getName().trim().isEmpty()) {
			if(regReq.getName().length() < 2) {
				errors.rejectValue("name", "short");
				return;
			} 
			if(regReq.getName().length() > 4) {
				errors.rejectValue("name", "long");
				return;
			} 
		}
	}

}
