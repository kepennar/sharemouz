package org.kepennar.sharemouz.backend.config.audit;

public class AuditableUser {
    private String name;

    public AuditableUser() {

    }

    public AuditableUser(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}