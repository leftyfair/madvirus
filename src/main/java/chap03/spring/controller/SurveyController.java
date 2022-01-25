package chap03.spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import chap03.spring.survey.AnseweredData;
import chap03.spring.survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	@ModelAttribute("questions")
	private List<Question> createQuestions() {
		Question q1 = new Question("당신 역할 무엇", Arrays.asList("서버", "프론트", "백엔드", "풀스텍", "기타"));
		Question q2 = new Question("많이 사용하는 개발 도구", Arrays.asList("이클립스", "인텔리제이", "비주얼스튜디오", "서브라임", "기타"));
		Question q3 = new Question("하고 싶은 말은?");
		return Arrays.asList(q1, q2, q3);
	}
	
	@GetMapping
	public String form(Model model) {
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String submit(AnseweredData data) {
		return "survey/submitted";
	}
}


