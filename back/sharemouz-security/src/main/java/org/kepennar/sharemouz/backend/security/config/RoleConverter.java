package org.kepennar.sharemouz.backend.security.config;

import org.kepennar.sharemouz.backend.security.Role;
import org.springframework.core.convert.converter.Converter;

public class RoleConverter {

    public static class StringToRoleConverter implements Converter<String, Role> {
        @Override
        public Role convert(String source) {
            return Role.getRoleByKey(source);
        }
    }

    public static class RoleToStringConverter implements Converter<Role, String> {
        @Override
        public String convert(Role source) {
            return source.key();
        }
    }

}
