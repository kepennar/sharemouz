package org.kepennar.sharemouz.backend.reservation;

import org.junit.Test;
import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.kepennar.sharemouz.backend.reservation.model.Reservation;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by kepennar on 02/08/14.
 */
public class FooTests {

    @Test
    public void reservationTest() {

        Offer anOffer = new Offer("Offer", "offer description");

        Instant now = Instant.now();
        Instant end = now.plus(3L, DAYS);

        Reservation aReservation = new Reservation(anOffer, now, end);

        System.out.println(aReservation);
    }
}
