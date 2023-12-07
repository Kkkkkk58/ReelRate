package ru.kslacker.reelrate.dto.motionpicture;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

@Getter
@ToString
public class SeriesCreateDto extends MotionPictureCreateDto {
    private final int season;

    public SeriesCreateDto(
            MotionPictureType type,
            String title,
            String description,
            List<Genre> genres,
            Integer durationMinutes,
            LocalDate releaseDate,
            int season) {
        super(type, title, description, genres, durationMinutes, releaseDate);
        this.season = season;
    }
}
