package org.kepennar.sharemouz.backend.site.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by kepennar on 02/08/14.
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibilityChecker(
                objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
                        .withGetterVisibility(JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
                        .withSetterVisibility(JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
                        .withCreatorVisibility(JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC));
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }
}
