package org.kepennar.sharemouz.backend;

/**
 * Created by kepennar on 09/08/14.
 */
public final class SpringProfiles {
    private SpringProfiles() {
        throw new UnsupportedOperationException();
    }

    public static final String SPRING_PROFILE_TEST = "test";
    public static final String NOT_SPRING_PROFILE_TEST = "!test";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
}
