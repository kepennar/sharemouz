package org.kepennar.sharemouz.backend.security;

import org.kepennar.sharemouz.backend.security.audit.AuditRepository;
import org.kepennar.sharemouz.backend.security.audit.MongoAuditEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class SecurityController {
    private final AuditRepository repo;

    @Inject
    public SecurityController(AuditRepository auditRepository) {
        this.repo= auditRepository;
    }

    @RequestMapping(method = GET, value = "login")
    public String login() {
        return "/login";

    }


    @RequestMapping(method = GET, value = "unsecured")
    public @ResponseBody HttpEntity<String> unsecured() {
        return new ResponseEntity<>("Okay!", OK);
    }


    @RequestMapping(method = GET, value = "api/secured")
    public @ResponseBody HttpEntity<String> secured() {
        return new ResponseEntity<>("Okay!", OK);

    }

    @RequestMapping(method = GET, value = "admin/audits")
    public @ResponseBody HttpEntity<List<MongoAuditEvent>> getEvents() {
        return new ResponseEntity<>(repo.findAll(), OK);
    }
}
