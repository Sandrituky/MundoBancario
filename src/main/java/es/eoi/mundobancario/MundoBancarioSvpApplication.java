package es.eoi.mundobancario;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import es.eoi.utils.DtoUtils;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MundoBancarioSvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MundoBancarioSvpApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public DtoUtils dtoUtils() {
	    return new DtoUtils();
	}

}
