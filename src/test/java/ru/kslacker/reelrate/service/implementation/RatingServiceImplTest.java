package ru.kslacker.reelrate.service.implementation;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.MotionPictureRatingView;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.service.api.RatingService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    @Mock
    private MotionPictureRatingRepository motionPictureRatingRepository;

    private RatingService ratingService;

    @BeforeEach
    void setUp() {
        this.ratingService = new RatingServiceImpl(motionPictureRatingRepository);
    }

    @Test
    void getRating_ratingNotFound_throws() {
        Long id = 1L;
        when(motionPictureRatingRepository.findByMotionPictureId(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> ratingService.getRating(id));
        verify(motionPictureRatingRepository, times(1)).findByMotionPictureId(id);
    }

    @Test
    void getRating_existingRating_isOkReturnsRating() {
        MotionPictureRatingView rating = getRating();
        Long id = rating.getMotionPicture().getId();
        when(motionPictureRatingRepository.findByMotionPictureId(id)).thenReturn(Optional.of(rating));

        MotionPictureRatingDto motionPictureRatingDto = ratingService.getRating(id);

        assertEquals(rating.getAverageScore(), motionPictureRatingDto.rating());
        assertEquals(rating.getNumberOfVotes(), motionPictureRatingDto.numberOfVotes());
        verify(motionPictureRatingRepository, times(1)).findByMotionPictureId(id);
    }

    private MotionPictureRatingView getRating() {
        return new MotionPictureRatingView(
                getMotionPicture(),
                7.5,
                42
        );
    }

    private MotionPicture getMotionPicture() {
        Movie movie = new Movie(
                "Test",
                "Test",
                Set.of(),
                Set.of(),
                Set.of(),
                1,
                LocalDate.now()
        );
        movie.setId(1L);

        return movie;
    }
}
