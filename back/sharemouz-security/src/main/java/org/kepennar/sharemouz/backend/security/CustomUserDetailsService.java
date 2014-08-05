package org.kepennar.sharemouz.backend.security;

import java.util.ArrayList;
import java.util.List;

import org.kepennar.sharemouz.backend.config.Role;
import org.kepennar.sharemouz.backend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	private final UserRepository repo;

	@Inject
	public CustomUserDetailsService(UserRepository userRepository) {
		this.repo = userRepository;
	}

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {

		List<OpenIDAttribute> attributes = token.getAttributes();

		String email = attributes.stream()
			.filter(a -> { return a.getName().equals("email"); })
			.map(a -> { return a.getValues().get(0); })
			.findFirst()
			.get();
		
		List<Role> roles = repo.findByEmail(email).getRoles();

		return new User(token.getName(), "", createAuthorityList(roles));
	}
	
	
	private List<GrantedAuthority> createAuthorityList(List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles.size());

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.key()));
        }
        return authorities;
	}

}