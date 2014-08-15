package org.kepennar.sharemouz.backend.security.audit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kepennar on 09/08/14.
 */
@Repository
public interface AuditRepository extends MongoRepository<MongoAuditEvent, String>{
    List<MongoAuditEvent> findByPrincipal(String principal);
}
