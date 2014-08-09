package org.kepennar.sharemouz.backend.security;

public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    private final String key;

    private Role(String key) {
		this.key = key;
	}

    public static Role getRoleByKey(String key) {
        for (Role role : Role.values()) {
            if (role.key().equals(key)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unable to find role " + key);
    }

    public String key() {
        return key;
    }

	
	
	
	
}
