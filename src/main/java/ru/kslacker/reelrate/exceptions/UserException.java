package ru.kslacker.reelrate.exceptions;

public class UserException extends ReelRateException {
    private UserException(String message) {
        super(message);
    }

    public static UserException usernameIsOccupied(String username) {
        return new UserException("Username " + username + " is already taken");
    }

    public static UserException emailIsOccupied(String email) {
        return new UserException("Email " + email + " is already registered");
    }
}
