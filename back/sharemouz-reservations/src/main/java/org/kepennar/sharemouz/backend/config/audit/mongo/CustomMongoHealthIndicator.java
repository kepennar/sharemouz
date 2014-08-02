package org.kepennar.sharemouz.backend.config.audit.mongo;

import com.mongodb.CommandResult;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.MongoHealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.inject.Inject;

/**
 * Created by kepennar on 02/08/14.
 */
@Component
public class CustomMongoHealthIndicator extends AbstractHealthIndicator {

    private final MongoTemplate mongoTemplate;

    @Inject
    public CustomMongoHealthIndicator(MongoTemplate mongoTemplate) {
        Assert.notNull(mongoTemplate, "MongoTemplate must not be null");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        CommandResult result = this.mongoTemplate.executeCommand("{ buildInfo: 1 }");
        builder.up().withDetail("Mongo version", result.getString("version"));
    }
}
