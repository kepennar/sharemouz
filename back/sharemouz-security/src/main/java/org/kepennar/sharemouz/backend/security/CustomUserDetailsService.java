package org.kepennar.sharemouz.backend.security;

import org.kepennar.sharemouz.backend.config.Role;
import org.kepennar.sharemouz.backend.model.QUser;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userDetailsService")
public class CustomUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
    private final static String ROLE_PREFIX = "ROLE_";
    private final UserRepository repo;

    @Inject
    public CustomUserDetailsService(UserRepository userRepository) {
		this.repo = userRepository;
	}

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {

		List<OpenIDAttribute> attributes = token.getAttributes();

        String email = getOpenIdAttribute(attributes, "email");

        org.kepennar.sharemouz.backend.model.User dbUser = repo.findOne(QUser.user.email.eq(email));
        if (dbUser == null) {
            String firstname = getOpenIdAttribute(attributes, "firstname");
            String lastname = getOpenIdAttribute(attributes, "lastname");

            dbUser = new org.kepennar.sharemouz.backend.model.User();
            dbUser.setEmail(email);
            dbUser.setFirstname(firstname);
            dbUser.setLastname(lastname);
            dbUser.setRoles(Arrays.asList(Role.USER));

            repo.save(dbUser);
        }

        List<Role> roles = dbUser.getRoles();

		return new User(token.getName(), "", createAuthorityList(roles));
	}
	
	
	private List<GrantedAuthority> createAuthorityList(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.size());

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.key()));
        }
        return authorities;
	}

    private String getOpenIdAttribute(List<OpenIDAttribute> attributes, String key) {
        return attributes.stream()
                .filter(a -> a.getName().equals(key))
                .map(a -> a.getValues().get(0))
                .findFirst()
                .get();
    }
}