package org.kepennar.sharemouz.backend;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	
	@RequestMapping(method = GET, value="unsecured")
	public HttpEntity<String> unsecured() {
        return new ResponseEntity<>("Okay!", OK);
               
    }
	
	
	@RequestMapping(method = GET, value="login")
	public HttpEntity<String> login() {
        return new ResponseEntity<>("Login page!", OK);
               
    }
	
	@RequestMapping(method = GET, value="api/secured")
	public HttpEntity<String> secured() {
        return new ResponseEntity<>("Okay!", OK);
               
    }
	

	@RequestMapping(method = GET, value="admin")
	public HttpEntity<String> admin() {
        return new ResponseEntity<>("Okay!", OK);
               
    }
}
