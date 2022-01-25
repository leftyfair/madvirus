package chap03.spring.survey;

import java.util.Arrays;
import java.util.List;

public class Example {

	public static void main(String[] args) {

		String title = "당신 역할 무엇";
		
		List<String> options = Arrays.asList("서버", "프론트", "백엔드", "풀스텍", "기타");

		Question q1 = new Question(title, options);
		System.out.println(q1.getTitle());
		System.out.println(q1.getOptions().get(0));
		System.out.println(q1.getOptions().get(1));
		System.out.println(q1.getOptions().get(2));
		System.out.println(q1.getOptions().get(3));
		System.out.println(q1.getOptions().get(4));
	}

}
