package org.kepennar.sharemouz.backend.config.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.kepennar.sharemouz.backend.config.audit.AuditableUser;
import org.kepennar.sharemouz.backend.config.audit.MyAuditor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import static org.kepennar.sharemouz.backend.config.Constants.NOT_SPRING_PROFILE_TEST;

@Profile(NOT_SPRING_PROFILE_TEST)
@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorProvider")
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${db.name}") private String dbName;
    @Value("${spring.data.mongodb.host}") private String dbHost;
    @Value("${spring.data.mongodb.port}") private String dbPort;

    @Bean
    public AuditorAware<AuditableUser> auditorProvider() {
        return new MyAuditor("TempTest");
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(dbHost + ":" + dbPort);
    }
}