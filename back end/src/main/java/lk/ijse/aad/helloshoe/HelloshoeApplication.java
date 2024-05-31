package lk.ijse.aad.helloshoe;

import lk.ijse.aad.helloshoe.controller.CustomerController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableJpaRepositories
public class HelloshoeApplication {
	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	Logger logger() {
		return LoggerFactory.getLogger(HelloshoeApplication.class);
	}

	@Bean
	DateTimeFormatter dateTimeFormatter() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}
	public static void main(String[] args) {
		SpringApplication.run(HelloshoeApplication.class, args);
	}
}
