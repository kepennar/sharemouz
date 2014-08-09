package org.kepennar.sharemouz.backend.site.config;

import org.kepennar.sharemouz.backend.offer.model.Offer;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

/**
 * Created by kepennar on 02/08/14.
 */
public class OptionalConverter {
    public static class ReservationToOptionalConverter implements Converter<Offer, Optional<?>> {
        @Override
        public Optional<?> convert(Offer offer) {
            return Optional.ofNullable(offer);
        }
    }

    public static class OptionalToReservationConverter implements Converter<Optional<Offer>, Offer> {
        @Override
        public Offer convert(Optional<Offer> optional) {
            return optional.map((r) -> r)
                    .orElse(null);
        }
    }
}
