package chap03.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import chap03.spring.dto.RegisterRequest;

@Controller
public class HomeController {

	
	@GetMapping("/")
	public String hello(Model model, String username) {
		model.addAttribute("name", "hell");
		return "home";
	}
}
