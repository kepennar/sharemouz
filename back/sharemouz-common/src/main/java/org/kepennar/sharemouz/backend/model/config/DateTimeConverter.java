package org.kepennar.sharemouz.backend.model.config;

import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by kepennar on 02/08/14.
 */
public class DateTimeConverter {
    public static class InstantToStringConverter implements Converter<Instant, String> {
        @Override
        public String convert(Instant instant) {
            return DecimalUtils.toDecimal(
                    instant.getEpochSecond(), instant.getNano());
        }
    }

    public static class StringToInstantConverter implements Converter<String, Instant> {
        @Override
        public Instant convert(String source) {
            if (source.length() == 0) {
                return null;
            }
            BigDecimal value = new BigDecimal(source);
            long seconds = value.longValue();
            int nanoseconds = DecimalUtils.extractNanosecondDecimal(value, seconds);
            return Instant.ofEpochSecond(seconds, nanoseconds);
        }
    }
}
