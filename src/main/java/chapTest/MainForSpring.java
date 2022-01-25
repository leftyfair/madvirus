package chapTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForSpring {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Conf.class);
		
		Hell hell = ctx.getBean("hell", Hell.class); 
		hell.greet();
	}
	
}
