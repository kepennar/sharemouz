package org.kepennar.sharemouz.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.xml.transform.Source;
import java.util.Arrays;
import java.util.List;

import static org.kepennar.sharemouz.backend.SpringProfiles.SPRING_PROFILE_TEST;

/**
 * Created by kepennar on 02/08/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = SharemouzApplication.class)
@ActiveProfiles(SPRING_PROFILE_TEST)
@IntegrationTest({"server.port=0", "management.port=0"})
public abstract class AbstractIntegrationTest {

    @Value("${local.server.port}")
    protected int port;

    protected RestTemplate restTemplate = new TestRestTemplate();

    protected MockMvc mockMvc;

    @Inject
    private WebApplicationContext wac;
    @Inject
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(objectMapper);

        List<HttpMessageConverter<?>> converters = Arrays.asList(
                new ByteArrayHttpMessageConverter(),
                new StringHttpMessageConverter(),
                new ResourceHttpMessageConverter(),
                new SourceHttpMessageConverter<Source>(),
                new AllEncompassingFormHttpMessageConverter(),
                jsonMessageConverter
        );

        restTemplate.setMessageConverters(converters);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
}

