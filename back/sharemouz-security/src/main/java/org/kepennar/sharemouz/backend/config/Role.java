package org.kepennar.sharemouz.backend.config;

public enum Role {
	
	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN");
	
	private final String key;
	
	private Role(String key) {
		this.key = key;
	}	
	public String key() {
		return key;
	}
	
	public static Role getRoleByKey(String key) {
		for (Role role : Role.values()) {
			if (role.key().equals(key)) {
				return role;
			}
		}
		throw new IllegalArgumentException("Unable to find role " + key);
	}


	public static final String ROLE_USER_VAL 	= "USER";
	public static final String ROLE_ADMIN_VAL 	= "ADMIN";
	
	
	
	
}
