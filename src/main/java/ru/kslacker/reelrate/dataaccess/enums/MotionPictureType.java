package ru.kslacker.reelrate.dataaccess.enums;

import lombok.Getter;

@Getter
public enum MotionPictureType {
    MOVIE(Values.MOVIE),
    SERIES(Values.SERIES),
    CARTOON(Values.CARTOON);

    private final String value;

    MotionPictureType(String value) {
        this.value = value;
    }

    public static class Values {
        public static final String MOVIE = "MOVIE";
        public static final String SERIES = "SERIES";
        public static final String CARTOON = "CARTOON";
    }
    }
