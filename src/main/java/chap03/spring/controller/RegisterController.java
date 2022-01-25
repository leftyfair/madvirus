package chap03.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chap03.spring.dto.RegisterRequest;
import chap03.spring.dto.RegisterRequestValidator;
import chap03.spring.exception.AlreadyExistingMemberException;
import chap03.spring.service.MemberRegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	
	@Autowired
	public RegisterController(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@GetMapping("/step1")
	public String handleStep1(Model model) {
		model.addAttribute("name", "헬가이");
		model.addAttribute("age", "22");
		return "register/step1";
	}
	
	@PostMapping("/step2")
	public String handleStep2(@RequestParam(defaultValue = "false") Boolean agree,
			RegisterRequest member) {
		if(!agree) {
			return "redirect:/register/step1";
		}
		return "register/step2";
	}
	
	@PostMapping("/step3")
	public String handleStep3(RegisterRequest member, Errors errors) {
		new RegisterRequestValidator().validate(member, errors);
		if(errors.hasErrors()) return "register/step2";
		
		try {
			memberRegisterService.register(member);	
			return "register/step3";
		} catch (AlreadyExistingMemberException e) {
			return "register/step2";
		}
	}
	
}
