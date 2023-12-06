package ru.kslacker.reelrate.dto.motionpicture;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;
import ru.kslacker.reelrate.dto.personrole.ActingPersonRoleDto;
import ru.kslacker.reelrate.dto.personrole.PersonRoleDto;

@Getter
@ToString
public class SeriesDto extends MotionPictureDto {
    private final int season;
    @Builder
    public SeriesDto(
            Long id,
            String title,
            String description,
            List<Genre> genres,
            List<PersonRoleDto> creators,
            List<ActingPersonRoleDto> cast,
            Integer durationMinutes,
            LocalDate releaseDate,
            Integer season) {

        super(id,
                MotionPictureType.CARTOON,
                title,
                description,
                genres,
                creators,
                cast,
                durationMinutes,
                releaseDate);

        this.season = season;
    }

    public static class SeriesDtoBuilder extends MotionPictureDtoBuilder {
        SeriesDtoBuilder() {
            super();
        }
    }

}
