package ru.kslacker.reelrate.dto.motionpicture;

import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

public record MotionPictureFiltersDto(
        MotionPictureType type,
        String title,
        Genre genre) {

}
