package org.kepennar.sharemouz.backend;

import org.kepennar.sharemouz.backend.site.config.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Created by kepennar on 09/08/14.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSpringDataWebSupport
public class SharemouzApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SharemouzApplication.class);
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
