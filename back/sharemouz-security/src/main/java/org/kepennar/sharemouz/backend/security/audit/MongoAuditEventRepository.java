package org.kepennar.sharemouz.backend.security.audit;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MongoAuditEventRepository implements AuditEventRepository {
    private final AuditRepository repo;

    private Function<MongoAuditEvent, AuditEvent> toAuditEvent = (m) -> {
        Map<String, Object> datas = new HashMap<>(m.getData());
        return new AuditEvent(m.getTimestamp(), m.getPrincipal(), m.getType(), datas);
    };

    @Inject
    public MongoAuditEventRepository(AuditRepository auditRepository) {
        this.repo= auditRepository;
    }

	@Override
	public List<AuditEvent> find(String principal, Date after) {
        return repo.findByPrincipal(principal).stream().map(toAuditEvent).collect(Collectors.toList());
	}

	@Override
	public void add(AuditEvent event) {
		MongoAuditEvent mAuditEvent = new MongoAuditEvent(event);
        repo.save(mAuditEvent);
	}

}
