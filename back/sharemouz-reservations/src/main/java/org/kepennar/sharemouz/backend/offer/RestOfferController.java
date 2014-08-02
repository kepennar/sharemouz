package org.kepennar.sharemouz.backend.offer;

import org.kepennar.sharemouz.backend.model.ValidationError;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kepennar on 02/08/14.
 */
@RestController
@RequestMapping("/api/offers")
public class RestOfferController {
    private final OfferRepository repo;

    @Inject
    public RestOfferController(OfferRepository OfferRepository) {
        this.repo = OfferRepository;
    }

    @RequestMapping(method = GET, value = "{id}")
    public HttpEntity<Offer> getOffer(@PathVariable String id) {
        HttpEntity<Offer> httpEntity = new ResponseEntity<>(NOT_FOUND);
        return Optional.ofNullable(repo.findOne(id))
                .map(r -> {
                    return new ResponseEntity<>(r, OK);
                })
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }


    @RequestMapping(method = DELETE, value = "{id}")
    public HttpEntity<Offer> deleteOffer(@PathVariable String id) {
        HttpEntity<Offer> httpEntity = new ResponseEntity<>(NOT_FOUND);
        repo.delete(id);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(method = POST)
    public HttpEntity<Offer> updateOffer(@RequestBody Offer offer) {
        Offer dbOffer = repo.findOne(offer.getId());
        dbOffer.updateProperties(offer);
        return new ResponseEntity<>(repo.save(dbOffer), OK);
    }

    @RequestMapping(method = PUT)
    public HttpEntity<Offer> createOffer(@RequestBody @Valid Offer offer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }
        Offer savedOffer = repo.save(offer);
        return new ResponseEntity<>(savedOffer, OK);
    }

}
