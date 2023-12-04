package ru.kslacker.reelrate.dataaccess.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.kslacker.reelrate.dataaccess.enums.Genre;
import ru.kslacker.reelrate.dataaccess.enums.MotionPictureType;

@Entity
@DiscriminatorValue(MotionPictureType.Values.CARTOON)
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cartoon extends MotionPicture {
    public Cartoon(
            String title,
            String description,
            Set<Genre> genres,
            Set<PersonRole> directors,
            Set<ActingPersonRole> cast,
            Integer durationMinutes,
            LocalDate releaseDate) {

        super(MotionPictureType.CARTOON,
                title,
                description,
                genres,
                directors,
                cast,
                durationMinutes,
                releaseDate);
    }
}
