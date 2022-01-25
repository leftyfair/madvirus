package chap03.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import chap03.spring.dto.AuthInfo;
import chap03.spring.dto.LoginCommand;
import chap03.spring.dto.LoginCommandValidator;
import chap03.spring.exception.WrongIdPasswordException;
import chap03.spring.service.AuthService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private AuthService authService;
	
	@Autowired
	public LoginController(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping
	public String form(LoginCommand loginCommand) {
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, Model model) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) return "login/loginForm";
		
		// 로그인 시도
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			model.addAttribute("authInfo", authInfo);
			//TODO: 세션저장
			
			return "login/loginSuccess";
		} catch (WrongIdPasswordException e) {
			errors.reject("wrongIdPasswordNotMatching");
			return "login/loginForm";
		}
		
		
		
	}
	
	
}
