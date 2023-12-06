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
import ru.kslacker.reelrate.dataaccess.visitors.MotionPictureVisitor;

@Entity
@DiscriminatorValue(MotionPictureType.Values.MOVIE)
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Movie extends MotionPicture {
    public Movie(
            String title,
            String description,
            Set<Genre> genres,
            Set<PersonRole> directors,
            Set<ActingPersonRole> cast,
            Integer durationMinutes,
            LocalDate releaseDate) {

        super(MotionPictureType.MOVIE,
                title,
                description,
                genres,
                directors,
                cast,
                durationMinutes,
                releaseDate);
    }

    @Override
    public void accept(MotionPictureVisitor visitor) {
        visitor.visit(this);
    }
}
