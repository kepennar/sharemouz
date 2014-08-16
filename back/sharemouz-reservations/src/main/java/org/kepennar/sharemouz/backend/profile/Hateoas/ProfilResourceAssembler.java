package org.kepennar.sharemouz.backend.profile.Hateoas;

import org.kepennar.sharemouz.backend.model.User;
import org.kepennar.sharemouz.backend.offer.web.RestOfferController;
import org.kepennar.sharemouz.backend.profile.web.RestProfileController;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by kepennar on 10/08/14.
 */
@Component
public class ProfilResourceAssembler implements ResourceAssembler<User, ProfileResource> {
    public static final String OFFERS_REL = "offers";
    public static final String RESERVATIONS_REL = "reservations";

    @Override
    public ProfileResource toResource(User u) {
        ProfileResource resource =new ProfileResource(u);

        String profilId= u.getId();
        resource.add(linkTo(methodOn(RestProfileController.class).getProfile(profilId)).withSelfRel());
        resource.add(linkTo(methodOn(RestOfferController.class).getProfilOffers(profilId, null, null)).withRel(OFFERS_REL));
        return resource;
    }
}
