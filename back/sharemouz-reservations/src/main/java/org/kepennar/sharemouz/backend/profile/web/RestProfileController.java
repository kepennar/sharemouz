package org.kepennar.sharemouz.backend.profile.web;

import org.kepennar.sharemouz.backend.profile.Hateoas.ProfilResourceAssembler;
import org.kepennar.sharemouz.backend.profile.Hateoas.ProfileResource;
import org.kepennar.sharemouz.backend.profile.services.ProfilService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.kepennar.sharemouz.backend.ApiUrls.ROOT_URL_PROFILES;
import static org.kepennar.sharemouz.backend.ApiUrls.URL_PROFILES_PROFILE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * Created by kepennar on 10/08/14.
 */
@RestController
@RequestMapping(ROOT_URL_PROFILES)
public class RestProfileController {

    private final ProfilService service;
    private final ProfilResourceAssembler resourceAssembler;

    @Inject
    public RestProfileController(ProfilService profilService, ProfilResourceAssembler profilResourceAssembler) {
        this.service = profilService;
        this.resourceAssembler= profilResourceAssembler;
    }

    @RequestMapping(method = GET, value = URL_PROFILES_PROFILE)
    public HttpEntity<ProfileResource> getProfile(@PathVariable("id") String id) {
        return service.find(id)
                .map(u -> new ResponseEntity<ProfileResource>(resourceAssembler.toResource(u), OK))
                .orElse(new ResponseEntity(NOT_FOUND));
    }
}
