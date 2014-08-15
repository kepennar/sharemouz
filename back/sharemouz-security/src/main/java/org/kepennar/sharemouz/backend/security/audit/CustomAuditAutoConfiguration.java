package org.kepennar.sharemouz.backend.security.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.boot.actuate.autoconfigure.AuditAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Created by kepennar on 09/08/14.
 */
public class CustomAuditAutoConfiguration extends AuditAutoConfiguration {

    @Autowired
    MongoAuditEventRepository mongoAuditEventRepository;

    @Override
    @Bean
    public AuditListener auditListener() throws Exception {
        return new AuditListener(this.mongoAuditEventRepository);
    }
}
