package org.kepennar.sharemouz.backend;

import org.kepennar.sharemouz.backend.config.audit.AuditRepository;
import org.kepennar.sharemouz.backend.config.audit.MongoAuditEvent;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MainController {

    @Inject
    private AuditRepository auditRepository;

    @RequestMapping(method = GET, value = "unsecured")
    public HttpEntity<String> unsecured() {
        return new ResponseEntity<>("Okay!", OK);

    }


    @RequestMapping(method = GET, value = "api/secured")
    public HttpEntity<String> secured() {
        return new ResponseEntity<>("Okay!", OK);

    }

    @RequestMapping(method = GET, value = "admin/audits")
    public
    @ResponseBody
    HttpEntity<List<MongoAuditEvent>> getEvents() {
        return new ResponseEntity<>(auditRepository.findAll(), OK);
    }
}
