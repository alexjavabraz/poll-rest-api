package br.com.bjbraz.poll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value={
		@PropertySource(value="classspath:swagger-config.properties", ignoreResourceNotFound = true)
})
public class PollApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollApiApplication.class, args);
	}



}
