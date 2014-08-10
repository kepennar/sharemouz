package org.kepennar.sharemouz.backend.profile.web;

import org.kepennar.sharemouz.backend.model.User;
import org.kepennar.sharemouz.backend.profile.Hateoas.ProfilResourceAssembler;
import org.kepennar.sharemouz.backend.profile.Hateoas.ProfileResource;
import org.kepennar.sharemouz.backend.security.repository.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.kepennar.sharemouz.backend.ApiUrls.ROOT_URL_PROFILES;
import static org.kepennar.sharemouz.backend.ApiUrls.URL_PROFILES_PROFILE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * Created by kepennar on 10/08/14.
 */
@RestController
@RequestMapping(ROOT_URL_PROFILES)
public class RestProfileController {

    private final UserRepository repo;
    private final ProfilResourceAssembler resourceAssembler;

    @Inject
    public RestProfileController(UserRepository userRepository, ProfilResourceAssembler profilResourceAssembler) {
        this.repo = userRepository;
        this.resourceAssembler= profilResourceAssembler;
    }

    @RequestMapping(method = GET, value = URL_PROFILES_PROFILE)
    public HttpEntity<ProfileResource> getProfile(@PathVariable("id") String id) {
        User user = repo.findOne(id);
        return new ResponseEntity<ProfileResource>(resourceAssembler.toResource(user), OK);
    }
}
