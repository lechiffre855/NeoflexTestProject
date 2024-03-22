package ru.lechiffre.neoflex.neoflex_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.lechiffre.neoflex.neoflex_project.config.HolidayConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(HolidayConfiguration.class)
public class NeoflexProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoflexProjectApplication.class, args);
	}

}
