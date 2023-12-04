package ru.kslacker.reelrate.dataaccess.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Rating(int value) {
    private static final int MIN_RATING_VALUE = 0;
    private static final int MAX_RATING_VALUE = 10;

    public Rating {
        if (value < MIN_RATING_VALUE || value > MAX_RATING_VALUE) {
            throw new IllegalArgumentException();
        }
    }

    public Rating() {
        this(MIN_RATING_VALUE);
    }
}
