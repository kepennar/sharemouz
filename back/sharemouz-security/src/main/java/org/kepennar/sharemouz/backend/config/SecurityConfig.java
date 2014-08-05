package org.kepennar.sharemouz.backend.config;

import org.kepennar.sharemouz.backend.security.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.google.inject.Inject;

import static org.kepennar.sharemouz.backend.config.Role.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject CustomUserDetailsService userDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user")
				.roles(ROLE_USER.key()).and().withUser("admin")
				.password("admin").roles(ROLE_USER.key(), ROLE_ADMIN.key());
	}

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
        .antMatchers("/api/**").hasRole(ROLE_USER.key())
        .anyRequest().anonymous()
        .and()
    .openidLogin()
        .loginPage("/login")
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