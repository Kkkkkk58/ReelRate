package ru.kslacker.reelrate.dto.motionpicture;


import java.time.LocalDate;
import java.util.List;

import io.leangen.graphql.annotations.types.GraphQLInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

@Getter
@ToString
@AllArgsConstructor
@Builder
@GraphQLInterface(name = "MotionPictureCreateDto", implementationAutoDiscovery = true)
public class MotionPictureCreateDto {
    private final MotionPictureType type;
    private final String title;
    private final String description;
    private final List<Genre> genres;
    private final Integer durationMinutes;
    private final LocalDate releaseDate;
}
