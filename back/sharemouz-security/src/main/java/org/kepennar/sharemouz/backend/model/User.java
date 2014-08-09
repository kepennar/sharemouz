package org.kepennar.sharemouz.backend.model;

import org.kepennar.sharemouz.backend.config.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
public class User {
	
	@Id
	private String id;

    @Field("email")
    private String email;

    @Field("firstname")
    private String firstname;
    @Field("lastname")
    private String lastname;
    @Field("username")
    private String username;

    @Field("roles")
    private List<Role> roles;


    public String getEmail() {
        return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

    public List<Role> getRoles() {
        return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
