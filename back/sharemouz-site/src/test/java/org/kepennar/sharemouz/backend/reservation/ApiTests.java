package org.kepennar.sharemouz.backend.reservation;

import org.junit.Before;
import org.junit.Test;
import org.kepennar.sharemouz.backend.AbstractIntegrationTest;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;
import org.kepennar.sharemouz.backend.security.model.User;
import org.springframework.core.ParameterizedTypeReference;
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
import static org.kepennar.sharemouz.backend.ApiUrls.*;
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
        Offer createdOffer = createTestOffer("Offer", "This offer should have an id after being saved", "testuser");
        assertThat(createdOffer).isNotNull();
        assertThat(createdOffer.getId()).isNotEmpty();
    }

    @Test
    public void createAReservationForAnOfferShouldReturnAnEntityWhithAnId() {
        Offer createdOffer = createTestOffer("Offer to reserve", "A description", "testuser");

        Instant begin = Instant.now();
        Instant end = begin.plus(3L, DAYS);
        Reservation reservation = createTestReservation(createdOffer, begin, end);
        assertThat(reservation).isNotNull();
        assertThat(reservation.getId()).isNotEmpty();

    }

    @Test
    public void listingReservationFromAnOfferShouldReturnResult() {


        Offer createdOffer = createTestOffer("Offer to list", "A description", "testuser");
        Instant begin = Instant.now();
        Instant end = begin.plus(3L, DAYS);
        createTestReservation(createdOffer, begin, end);

        Map<String, String> uriVariables = new HashMap<>();
        ParameterizedTypeReference pageReference = new ParameterizedTypeReference<PagedResources<Resource<Reservation>>>() {
        };
        uriVariables.put("page", "0");
        uriVariables.put("size", "10");
        ResponseEntity<PagedResources<Resource<Reservation>>> offers =
                restTemplate.exchange(reservationsUrl + "/offer/" + createdOffer.getId(), GET, null, pageReference, uriVariables);
        assertThat(offers.getBody().getContent()).isNotEmpty();
    }


    private Offer createTestOffer(String name, String description, String username) {
        checkArgument(!isEmpty(name), "Offer name shouldn't be empty");
        checkArgument(!isEmpty(description), "Offer description shouldn't be empty");
        checkArgument(!isEmpty(username), "Username shouldn't be empty");

        Map<String, String> uriVariables = new HashMap<>();

        Offer anOffer = new Offer(name, description);
        User aUser = new User();
        aUser.setUsername(username);
        anOffer.setUser(aUser);
        ResponseEntity<Resource<Offer>> response = restTemplate.exchange(offersUrl, PUT, new HttpEntity<>(anOffer),
                new ParameterizedTypeReference<Resource<Offer>>() { }, uriVariables);

        return response.getBody().getContent();

    }

    private Reservation createTestReservation(Offer offer, Instant begin, Instant end) {
        checkNotNull(offer, "Offer shouldn't be null");
        checkArgument(!isEmpty(offer.getId()), "Offer id shouldn't be empty");
        checkNotNull(begin, "begin shouldn't be null");
        checkNotNull(end, "end shouldn't be null");

        Map<String, String> uriVariables = new HashMap<>();

        Reservation aReservation = new Reservation(offer, begin, end);
        ResponseEntity<Resource<Reservation>> response = restTemplate.exchange(reservationsUrl + URL_RESERVATIONS_RESERVE, PUT,
                new HttpEntity<>(aReservation), new ParameterizedTypeReference<Resource<Reservation>>() { }, uriVariables);

        return response.getBody().getContent();

    }
}
