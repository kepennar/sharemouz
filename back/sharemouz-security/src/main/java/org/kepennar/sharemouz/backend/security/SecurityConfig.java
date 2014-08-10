package org.kepennar.sharemouz.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.kepennar.sharemouz.backend.SpringProfiles.NOT_SPRING_PROFILE_TEST;
import static org.kepennar.sharemouz.backend.model.Role.ADMIN;
import static org.kepennar.sharemouz.backend.model.Role.USER;

@Profile(NOT_SPRING_PROFILE_TEST)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole(ADMIN.key())
                .antMatchers("/api/**").hasRole(USER.key())
                .antMatchers("/**").permitAll()
            .and()
            .openidLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/api/secured")
                .permitAll()
                .authenticationUserDetailsService(userDetailService)
                .attributeExchange("https://www.google.com/.*")
                    .attribute("email")
                    .type("http://axschema.org/contact/email")
                    .required(true)
                .and()
                    .attribute("firstname")
                    .type("http://axschema.org/namePerson/first")
                    .required(true)
                .and()
                    .attribute("lastname")
                    .type("http://axschema.org/namePerson/last")
                    .required(true)
                .and()
                .and()
                .attributeExchange(".*myopenid.com.*")
                    .attribute("email")
                    .type("http://schema.openid.net/contact/email")
                    .required(true)
                .and()
                    .attribute("fullname")
                    .type("http://schema.openid.net/namePerson")
                    .required(true);
    }
}