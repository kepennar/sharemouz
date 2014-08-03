package org.kepennar.sharemouz.backend.offer.web;

import org.kepennar.sharemouz.backend.ApiUrls;
import org.kepennar.sharemouz.backend.offer.exception.OfferPhotoReadException;
import org.kepennar.sharemouz.backend.offer.model.OfferPhoto;
import org.kepennar.sharemouz.backend.offer.services.OffersService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by kepennar on 03/08/14.
 */
@Controller
@RequestMapping(value = ApiUrls.URL_OFFERS_OFFER_PHOTO)
public class OfferPhotoController {

    private final OffersService service;

    @Inject
    public OfferPhotoController(OffersService offersService) {
        this.service = offersService;
    }

    @RequestMapping(method = GET)
    public HttpEntity<byte[]> loadOfferPhoto(@PathVariable String id) throws Exception {
        OfferPhoto offerPhoto = service.readOfferPhoto(id);
        if (offerPhoto != null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(offerPhoto.getMediaType());
            return new ResponseEntity<byte[]>(offerPhoto.getPhoto(), httpHeaders, HttpStatus.OK);
        }
        throw new OfferPhotoReadException(id);

    }

}
