package org.kepennar.sharemouz.backend;

import org.kepennar.sharemouz.backend.config.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class TestReservationApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TestReservationApplication.class);
        app.setAdditionalProfiles(Constants.SPRING_PROFILE_TEST);
        app.run(args);
    }

}
