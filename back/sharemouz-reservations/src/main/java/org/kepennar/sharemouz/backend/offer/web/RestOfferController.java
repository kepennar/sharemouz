package org.kepennar.sharemouz.backend.offer.web;

import org.kepennar.sharemouz.backend.ApiUrls;
import org.kepennar.sharemouz.backend.model.ValidationError;
import org.kepennar.sharemouz.backend.offer.hateoas.OfferResourceAssembler;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.offer.services.OffersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by kepennar on 02/08/14.
 */
@RestController
@RequestMapping(ApiUrls.ROOT_URL_OFFERS)
public class RestOfferController {
    private final OffersService service;
    private final OfferResourceAssembler assembler;

    @Inject
    public RestOfferController(OffersService offersService, OfferResourceAssembler offerResourceAssembler) {
        this.service = offersService;
        this.assembler = offerResourceAssembler;
    }

    // GET by offer id
    @RequestMapping(method = GET, value = ApiUrls.URL_OFFERS_OFFER)
    public HttpEntity<Resource<Offer>> getOffer(@PathVariable String id) {
        return service.findById(id)
                .map(r -> {
                    return new ResponseEntity<>(assembler.toResource(r), OK);
                })
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    //GET offers page
    @RequestMapping(method = GET, params = {"page", "size"})
    public HttpEntity<PagedResources<Resource<Offer>>> getOfferPaginated(Pageable pageable, PagedResourcesAssembler<Offer> pagedAssembler) {
        Page<Offer> orders = service.find(pageable);
        return new ResponseEntity<>(pagedAssembler.toResource(orders, assembler), OK);
    }


    // DELETE by offer id
    @RequestMapping(method = DELETE, value = ApiUrls.URL_OFFERS_OFFER)
    public HttpEntity deleteOffer(@PathVariable String id) {
        service.deleteById(id);
        return new ResponseEntity<>(OK);
    }

    // POST: Update an offer
    @RequestMapping(method = POST)
    public HttpEntity<Resource<Offer>> updateOffer(@RequestBody Offer offer) {
        return service.update(offer)
                .map(r -> {
                    return new ResponseEntity<>(assembler.toResource(r), OK);
                })
                .orElse(new ResponseEntity<>(NOT_FOUND));
    }

    // PUT: create an offer
    @RequestMapping(method = PUT)
    public HttpEntity<Resource<Offer>> createOffer(@RequestBody @Valid Offer offer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(ValidationError.of(bindingResult), BAD_REQUEST);
        }
        return new ResponseEntity<>(assembler.toResource(service.create(offer)), OK);
    }

}
