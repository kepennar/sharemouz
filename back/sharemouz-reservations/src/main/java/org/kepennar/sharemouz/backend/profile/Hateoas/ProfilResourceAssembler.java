package org.kepennar.sharemouz.backend.profile.Hateoas;

import org.kepennar.sharemouz.backend.model.User;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

/**
 * Created by kepennar on 10/08/14.
 */
@Component
public class ProfilResourceAssembler implements ResourceAssembler<User, ProfileResource> {
    @Override
    public ProfileResource toResource(User u) {
        return new ProfileResource(u);
    }
}
