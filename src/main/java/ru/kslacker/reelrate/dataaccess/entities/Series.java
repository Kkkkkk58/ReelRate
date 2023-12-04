package ru.kslacker.reelrate.dataaccess.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

@Entity
@DiscriminatorValue(MotionPictureType.Values.SERIES)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Series extends MotionPicture {
    private Integer season;

    public Series(
            String title,
            String description,
            Set<Genre> genres,
            Set<PersonRole> directors,
            Set<ActingPersonRole> cast,
            Integer durationMinutes,
            LocalDate releaseDate,
            Integer season) {

        super(MotionPictureType.SERIES,
                title,
                description,
                genres,
                directors,
                cast,
                durationMinutes,
                releaseDate);
        this.season = season;
    }
}
