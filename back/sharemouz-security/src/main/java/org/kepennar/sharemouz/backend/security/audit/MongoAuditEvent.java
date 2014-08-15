package org.kepennar.sharemouz.backend.security.audit;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kepennar on 09/08/14.
 */
@Document(collection = "auditEvent")
public class MongoAuditEvent {
    @Id
    private String id;
    private Date timestamp;
    private String principal;
    private String type;
    private Map<String, String> data = new HashMap<>();

    public MongoAuditEvent() { }
    public MongoAuditEvent(AuditEvent auditEvent) {
        this.timestamp= auditEvent.getTimestamp();
        this.principal= auditEvent.getPrincipal();
        this.type= auditEvent.getType();
        Map<String, Object> datas = auditEvent.getData();
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            this.data.put(entry.getKey(), entry.getValue().toString());
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
