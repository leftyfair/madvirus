package chapTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
	
	@Bean
	public Messa messa() {
		return new Messa();
	}
	
	@Bean
	public Hell hell() {
		return new Hell(messa());
	}
	

}
