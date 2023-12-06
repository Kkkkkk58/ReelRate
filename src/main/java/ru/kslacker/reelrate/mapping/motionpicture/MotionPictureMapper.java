package ru.kslacker.reelrate.mapping.motionpicture;

import ru.kslacker.reelrate.dataaccess.entities.Cartoon;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.entities.Series;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.motionpicture.SeriesDto;
import ru.kslacker.reelrate.mapping.personrole.PersonRoleMapper;

public class MotionPictureMapper {
    public static MotionPictureDto map(MotionPicture motionPicture) {
        return MotionPictureDto.builder()
                .id(motionPicture.getId())
                .type(motionPicture.getType())
                .title(motionPicture.getTitle())
                .description(motionPicture.getDescription())
                .genres(motionPicture.getGenres().stream().toList())
                .durationMinutes(motionPicture.getDurationMinutes())
                .releaseDate(motionPicture.getReleaseDate())
                .creators(motionPicture.getCreators().stream().map(PersonRoleMapper::map).toList())
                .cast(motionPicture.getCast().stream().map(PersonRoleMapper::map).toList())
                .build();
    }

    public static MotionPictureDto map(Movie movie) {
        return map((MotionPicture) movie);
    }

    public static MotionPictureDto map(Cartoon cartoon) {
        return map((MotionPicture) cartoon);
    }

    public static MotionPictureDto map(Series series) {
        return SeriesDto.builder()
                .season(series.getSeason())
                .id(series.getId())
                .title(series.getTitle())
                .description(series.getDescription())
                .genres(series.getGenres().stream().toList())
                .durationMinutes(series.getDurationMinutes())
                .releaseDate(series.getReleaseDate())
                .creators(series.getCreators().stream().map(PersonRoleMapper::map).toList())
                .cast(series.getCast().stream().map(PersonRoleMapper::map).toList())
                .build();

    }
}
