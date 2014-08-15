package org.kepennar.sharemouz.backend.profile.Hateoas;

import org.kepennar.sharemouz.backend.model.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by kepennar on 10/08/14.
 */
public class ProfileResource extends ResourceSupport {
    private String email;

    private String firstname;
    private String lastname;

    private Integer nbOffers;
    private Integer nbReservations;

    public ProfileResource() {
    }

    public ProfileResource(User u) {
        this.email = u.getEmail();
        this.firstname = u.getFirstname();
        this.lastname = u.getLastname();
        this.nbOffers= u.getNbOffers();
        this.nbReservations= u.getNbReservations();
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

    public Integer getNbOffers() {
        return nbOffers;
    }

    public void setNbOffers(Integer nbOffers) {
        this.nbOffers = nbOffers;
    }

    public Integer getNbReservations() {
        return nbReservations;
    }

    public void setNbReservations(Integer nbReservations) {
        this.nbReservations = nbReservations;
    }
}
