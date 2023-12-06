package ru.kslacker.reelrate.exceptions;

public class EntityNotFoundException extends ReelRateException {
    public <T> EntityNotFoundException(T id) {
        super("Entity with id " + id + " not found");
    }
}
