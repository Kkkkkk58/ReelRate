package ru.kslacker.reelrate.dto.motionpicture;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;
import ru.kslacker.reelrate.dto.personrole.ActingPersonRoleCreateDto;
import ru.kslacker.reelrate.dto.personrole.PersonRoleCreateDto;

@Getter
@ToString
public class SeriesCreateDto extends MotionPictureCreateDto {
    private final int season;

    @Builder
    public SeriesCreateDto(
            MotionPictureType type,
            String title,
            String description,
            List<Genre> genres,
            List<PersonRoleCreateDto> creators,
            List<ActingPersonRoleCreateDto> cast,
            Integer durationMinutes,
            LocalDate releaseDate,
            int season) {
        super(type, title, description, genres, creators, cast, durationMinutes, releaseDate);
        this.season = season;
    }
}
