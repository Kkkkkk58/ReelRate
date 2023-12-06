package ru.kslacker.reelrate.service.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;
import ru.kslacker.reelrate.dataaccess.entities.UserRating;
import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dataaccess.repositories.ReelRateUserRepository;
import ru.kslacker.reelrate.dataaccess.repositories.UserRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.service.api.ReelRateUserService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReelRateUserServiceImplTest {

    @Mock
    private UserRatingRepository userRatingRepository;
    @Mock
    private ReelRateUserRepository reelRateUserRepository;
    @Mock
    private MotionPictureRepository motionPictureRepository;

    private ReelRateUserService reelRateUserService;

    @BeforeEach
    void setUp() {
        this.reelRateUserService = new ReelRateUserServiceImpl(
                userRatingRepository,
                reelRateUserRepository,
                motionPictureRepository
        );
    }

    @Test
    void getById_existingUser_isOkReturnsUser() {
        ReelRateUser user = getReelRateUser();
        UUID id = user.getId();
        when(reelRateUserRepository.findById(id)).thenReturn(Optional.of(user));

        ReelRateUserDto userDto = reelRateUserService.getById(id);

        assertEquals(user.getId(), userDto.id());
        verify(reelRateUserRepository, times(1)).findById(id);
    }

    @Test
    void getById_nonExistentUser_throwsException() {
        UUID id = UUID.randomUUID();
        when(reelRateUserRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reelRateUserService.getById(id));
        verify(reelRateUserRepository, times(1)).findById(id);
    }

    @Test
    void rate_existingUserAndMotionPicture_isOkReturnsRating() {
        UserRating rating = getRating();
        when(reelRateUserRepository.existsById(rating.getUserId())).thenReturn(true);
        when(motionPictureRepository.existsById(rating.getMotionPictureId())).thenReturn(true);
        when(userRatingRepository.findById(any())).thenReturn(Optional.empty());
        when(userRatingRepository.saveAndFlush(any())).thenReturn(rating);

        UserRatingDto ratingDto = reelRateUserService.rate(
                rating.getUserId(),
                rating.getMotionPictureId(),
                rating.getRating());

        assertAll(
                () -> assertEquals(rating.getUserId(), ratingDto.reelRateUserId()),
                () -> assertEquals(rating.getMotionPictureId(), ratingDto.motionPictureId()),
                () -> assertEquals(rating.getRating(), ratingDto.rating())
        );
    }

    @Test
    void rate_nonExistentUser_throwsException() {
        UserRating rating = getRating();
        when(reelRateUserRepository.existsById(rating.getUserId())).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> reelRateUserService.rate(
                        rating.getUserId(),
                        rating.getMotionPictureId(),
                        rating.getRating())
        );
    }

    @Test
    void rate_nonExistentMotionPicture_throwsException() {
        UserRating rating = getRating();
        when(reelRateUserRepository.existsById(rating.getUserId())).thenReturn(true);
        when(motionPictureRepository.existsById(rating.getMotionPictureId())).thenReturn(false);

        assertThrows(
                EntityNotFoundException.class,
                () -> reelRateUserService.rate(
                        rating.getUserId(),
                        rating.getMotionPictureId(),
                        rating.getRating())
        );
    }

    @Test
    void getRatings_existingUser_isOkReturnsRatings() {
        ReelRateUser user = getReelRateUser();
        UUID userId = user.getId();
        when(reelRateUserRepository.findById(userId)).thenReturn(Optional.of(user));

        List<UserRatingDto> ratings = reelRateUserService.getRatings(userId);

        assertEquals(user.getRatings().size(), ratings.size());
    }

    @Test
    void getRatings_nonExistentUser_throwsException() {
        UUID userId = UUID.randomUUID();
        when(reelRateUserRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reelRateUserService.getRatings(userId));
    }

    @Test
    void getWatchLater_existingUser_isOkReturnsWatchLater() {
        ReelRateUser user = getReelRateUser();
        UUID userId = user.getId();
        when(reelRateUserRepository.findById(userId)).thenReturn(Optional.of(user));

        List<MotionPictureDto> watchLater = reelRateUserService.getWatchLater(userId);

        assertEquals(user.getWatchLater().size(), watchLater.size());
    }

    @Test
    void getWatchLater_nonExistentUser_throwsException() {
        UUID userId = UUID.randomUUID();
        when(reelRateUserRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reelRateUserService.getWatchLater(userId));
    }

    private UserRating getRating() {
        return new UserRating(
                UUID.randomUUID(),
                1L,
                new Rating(8)
        );
    }
    private ReelRateUser getReelRateUser() {
        UUID userId = UUID.randomUUID();
        Long motionPictureId = 1L;

        return new ReelRateUser(
                userId,
                Set.of(new UserRating(userId, motionPictureId, new Rating(8))),
                Set.of(getTestMotionPicture())
        );
    }

    private MotionPicture getTestMotionPicture() {
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