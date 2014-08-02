package org.kepennar.sharemouz.backend.reservation;

import org.junit.Test;
import org.kepennar.sharemouz.backend.AbstractIntegrationTest;
import org.kepennar.sharemouz.backend.offer.Offer;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.PUT;

/**
 * Created by kepennar on 02/08/14.
 */
public class ApiTests extends AbstractIntegrationTest {

    @Test
    public void testProfile() {
        String baseUrl = "http://localhost:" + port + "/";
        String offersUrl = baseUrl + "api/offers";
        String reservationsUrl = baseUrl + "api/reservations";


        Offer anOffer = new Offer("Offer", null);
        Map<String, String> uriVariables = new HashMap<>();
        ResponseEntity<Offer> response = restTemplate.exchange(offersUrl, PUT, new HttpEntity<>(anOffer), Offer.class);

        Instant now = Instant.now();
        Instant end = now.plus(3L, DAYS);
        Offer savedOffer = response.getBody();
        Reservation aReservation = new Reservation(savedOffer, now, end);
        ResponseEntity<Reservation> responseReserv = restTemplate.exchange(reservationsUrl + "/reserve", PUT,
                new HttpEntity<>(aReservation), Reservation.class);
        assertThat(responseReserv.getBody().getId()).isNotNull();

        ResponseEntity<Reservation[]> offers = restTemplate.getForEntity(reservationsUrl + "/offer/" + savedOffer.getId(), Reservation[].class);
        assertThat(offers.getBody()).isNotEmpty();
    }

}
