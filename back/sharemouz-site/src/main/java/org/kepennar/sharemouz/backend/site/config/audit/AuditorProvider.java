package org.kepennar.sharemouz.backend.site.config.audit;

import org.kepennar.sharemouz.backend.security.model.QUser;
import org.kepennar.sharemouz.backend.security.model.User;
import org.kepennar.sharemouz.backend.security.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("auditorProvider")
public class AuditorProvider implements AuditorAware<User>, ApplicationListener<ContextRefreshedEvent> {
    private final UserRepository repo;

    private User systemUser;

    @Inject
    public AuditorProvider(UserRepository userRepository) {
        this.repo= userRepository;
    }


    @Override
    public User getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal;
        if (authentication == null || !authentication.isAuthenticated()) {
                principal = systemUser;
        } else {
            UserDetails authUser = (UserDetails) authentication.getPrincipal();
            principal = repo.findOne(QUser.user.username.eq(authUser.getUsername()));
        }
        return principal;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (this.systemUser == null) {
            systemUser = new User("sysem@sharemouz.org", "The", "System", "Sharemouz");
        }
    }

}