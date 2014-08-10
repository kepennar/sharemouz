package org.kepennar.sharemouz.backend.reservation;

import org.junit.Before;
import org.junit.Test;
import org.kepennar.sharemouz.backend.AbstractIntegrationTest;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.hateoas.ReservationResource;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.kepennar.sharemouz.backend.ApiUrls.ROOT_URL_OFFERS;
import static org.kepennar.sharemouz.backend.ApiUrls.ROOT_URL_RESERVATIONS;
import static org.kepennar.sharemouz.backend.offer.hateoas.OfferResourceAssembler.LIST_RESERVATIONS_REL;
import static org.kepennar.sharemouz.backend.offer.hateoas.OfferResourceAssembler.RESERVE_REL;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by kepennar on 02/08/14.
 */
public class ApiTests extends AbstractIntegrationTest {
    private String baseUrl;
    private String offersUrl;
    private String reservationsUrl;

    @Before
    public void init() {
        baseUrl = "http://localhost:" + port + "/";
        offersUrl = baseUrl + ROOT_URL_OFFERS;
        reservationsUrl = baseUrl + ROOT_URL_RESERVATIONS;
    }

    @Test
    public void createAnOfferShouldReturnAnEntityWithAnId() {
        Resource<Offer> createdOfferResource = createTestOffer("Offer", "This offer should have an id after being saved");
        assertThat(createdOfferResource).isNotNull();
        assertThat(createdOfferResource.getLink(Link.REL_SELF).getHref()).isNotEmpty();
    }

    @Test
    public void createAReservationForAnOfferShouldReturnAnEntityWhithAnId() {
        Resource<Offer> createdOfferResource = createTestOffer("Offer to reserve", "A description");
        String reservationLink = createdOfferResource.getLink(RESERVE_REL).getHref();

        Instant begin = Instant.now();
        Instant end = begin.plus(3L, DAYS);
        ReservationResource reservation = createTestReservation(reservationLink, begin, end);

        assertThat(reservation).isNotNull();
        assertThat(reservation.getCreatedAt()).isNotNull();

    }

    @Test
    public void listingReservationFromAnOfferShouldReturnResult() {

        Resource<Offer> createdOfferResource = createTestOffer("Offer to list", "A description");
        String reservationLink = createdOfferResource.getLink(RESERVE_REL).getHref();
        String listReservationsLink = createdOfferResource.getLink(LIST_RESERVATIONS_REL).getHref().replaceAll("\\{([\\?\\&#/]?)([\\w\\,]+)\\}", "?page={page}&size={size}");
        Instant begin = Instant.now();
        Instant end = begin.plus(3L, DAYS);
        createTestReservation(reservationLink, begin, end);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("page", "0");
        uriVariables.put("size", "5");
        ParameterizedTypeReference pageReference = new ParameterizedTypeReference<PagedResources<ReservationResource>>() {  };
        ResponseEntity<PagedResources<ReservationResource>> offers =
                restTemplate.exchange(listReservationsLink, GET, null, pageReference, uriVariables);
        assertThat(offers.getBody().getContent()).isNotEmpty();
    }


    private Resource<Offer> createTestOffer(String name, String description) {
        checkArgument(!isEmpty(name), "Offer name shouldn't be empty");
        checkArgument(!isEmpty(description), "Offer description shouldn't be empty");

        Map<String, String> uriVariables = new HashMap<>();

        Offer anOffer = new Offer(name, description);
        ResponseEntity<Resource<Offer>> response = restTemplate.exchange(offersUrl, PUT, new HttpEntity<>(anOffer),
                new ParameterizedTypeReference<Resource<Offer>>() { }, uriVariables);

        return response.getBody();

    }

    private ReservationResource createTestReservation(String reservationLink, Instant begin, Instant end) {
        checkNotNull(reservationLink, "Reservation link shouldn't be null");
        checkNotNull(begin, "begin shouldn't be null");
        checkNotNull(end, "end shouldn't be null");

        Map<String, String> uriVariables = new HashMap<>();

        Reservation aReservation = new Reservation(begin, end);
        ResponseEntity<ReservationResource> response = restTemplate.exchange(reservationLink, PUT,
                new HttpEntity<>(aReservation), new ParameterizedTypeReference<ReservationResource>() { }, uriVariables);

        return response.getBody();

    }
}
