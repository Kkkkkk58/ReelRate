package ru.kslacker.reelrate.exceptions;

public class UserBuilderException extends ReelRateException {

    private UserBuilderException(String message) {
        super(message);
    }

    public static UserBuilderException missingCredentials() {
        return new UserBuilderException("Cannot create user with missing credentials");
    }

    public static UserBuilderException missingReelRateUser() {
        return new UserBuilderException("Cannot create user without ReelRate account");
    }
}
