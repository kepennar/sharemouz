package org.kepennar.sharemouz.backend.config.mongo;

import static org.kepennar.sharemouz.backend.config.RoleConverter.RoleToStringConverter;
import static org.kepennar.sharemouz.backend.config.RoleConverter.StringToRoleConverter;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
//@EnableMongoAuditing(auditorAwareRef = "auditorProvider")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.name}")
    String name;
    @Value("${mongo.username}")
    String username;
    @Value("${mongo.password}")
    String password;

    @Value("${spring.data.mongodb.host}")
    String host;
    @Value("${spring.data.mongodb.port}")
    String port;


    @Override
    protected String getDatabaseName() {
        return name;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host + ":" + port);
    }
/*
    @Bean
    public AuditorAware<AuditableUser> auditorProvider() {
        return new MyAuditor("TempTest");
    }
*/

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(username, password);
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(
               new StringToRoleConverter(), new RoleToStringConverter()));
    }

}