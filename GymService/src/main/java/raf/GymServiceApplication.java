package raf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // force scan JPA entities
@ComponentScan({"raf.security"})

public class GymServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymServiceApplication.class, args);
	}

}
