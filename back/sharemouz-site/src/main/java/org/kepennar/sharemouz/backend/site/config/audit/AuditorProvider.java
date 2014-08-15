package org.kepennar.sharemouz.backend.site.config.audit;

import org.kepennar.sharemouz.backend.model.User;
import org.kepennar.sharemouz.backend.security.AuthenticatedUserProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("auditorProvider")
public class AuditorProvider implements AuditorAware<User> {
    private final AuthenticatedUserProvider provider;

    @Inject
    public AuditorProvider(AuthenticatedUserProvider authenticatedUserProvider) {
        this.provider= authenticatedUserProvider;
    }

    @Override
    public User getCurrentAuditor() {
        return provider.getCurrent();
    }

}