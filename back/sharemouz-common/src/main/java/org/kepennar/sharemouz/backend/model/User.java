package org.kepennar.sharemouz.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User extends AbstractDocument {

    private String email;

    private String firstname;
    private String lastname;
    private String username;

    private List<Role> roles;

    public User() { }

    public User(String email, String firstname, String lastname, String username) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    public String getEmail() {
        return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
