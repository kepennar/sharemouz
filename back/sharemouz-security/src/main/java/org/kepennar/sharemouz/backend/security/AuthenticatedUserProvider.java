package org.kepennar.sharemouz.backend.security;

import org.kepennar.sharemouz.backend.model.QUser;
import org.kepennar.sharemouz.backend.model.User;
import org.kepennar.sharemouz.backend.security.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by kepennar on 14/08/14.
 */
@Component
public class AuthenticatedUserProvider implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository repo;

    private User systemUser;

    @Inject
    public AuthenticatedUserProvider(UserRepository userRepository) {
        this.repo = userRepository;
    }

    public User getCurrent() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (authentication == null || !authentication.isAuthenticated()) {
            user = systemUser;
        } else {
            UserDetails authUser = (UserDetails) authentication.getPrincipal();
            user = repo.findOne(QUser.user.username.eq(authUser.getUsername()));
        }
        return user;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (this.systemUser == null) {
            this.systemUser = new User("sysem@sharemouz.org", "The", "System", "Sharemouz");
        }
    }

}
