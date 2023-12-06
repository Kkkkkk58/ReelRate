package ru.kslacker.reelrate.dto.motionpicture;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;
import ru.kslacker.reelrate.dto.personrole.ActingPersonRoleDto;
import ru.kslacker.reelrate.dto.personrole.PersonRoleDto;

@ToString
public class MovieDto extends MotionPictureDto {
    @Builder
    public MovieDto(
            Long id,
            String title,
            String description,
            List<Genre> genres,
            List<PersonRoleDto> creators,
            List<ActingPersonRoleDto> cast,
            Integer durationMinutes,
            LocalDate releaseDate) {

        super(id,
                MotionPictureType.CARTOON,
                title,
                description,
                genres,
                creators,
                cast,
                durationMinutes,
                releaseDate);
    }

    public static class MovieDtoBuilder extends MotionPictureDtoBuilder {
        MovieDtoBuilder() {
            super();
        }
    }
}
