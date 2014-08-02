package org.kepennar.sharemouz.backend;

import org.kepennar.sharemouz.backend.config.Constants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ReservationApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ReservationApplication.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
		app.addListeners(new ApplicationPidListener("app.pid"));
		
		// Check if the selected profile has been set as argument.
		// if not the production profile will be added
		addDefaultProfile(app, source);
		app.run(args);
	}
	

	/**
	 * Set a default profile if it has not been set
	 */
	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")) {
			app.setAdditionalProfiles(Constants.SPRING_PROFILE_PRODUCTION);
		}
	}
}
