package ru.kslacker.reelrate.service.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;
import ru.kslacker.reelrate.dataaccess.entities.UserRating;
import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dataaccess.models.UserRatingId;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dataaccess.repositories.ReelRateUserRepository;
import ru.kslacker.reelrate.dataaccess.repositories.UserRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.mapping.reelrateuser.ReelRateUserMapper;
import ru.kslacker.reelrate.mapping.reelrateuser.UserRatingMapper;
import ru.kslacker.reelrate.service.api.ReelRateUserService;

@Service
public class ReelRateUserServiceImpl implements ReelRateUserService {
    private final UserRatingRepository userRatingRepository;
    private final ReelRateUserRepository reelRateUserRepository;
    private final MotionPictureRepository motionPictureRepository;

    public ReelRateUserServiceImpl(
            UserRatingRepository userRatingRepository,
            ReelRateUserRepository reelRateUserRepository,
            MotionPictureRepository motionPictureRepository) {
        this.userRatingRepository = userRatingRepository;
        this.reelRateUserRepository = reelRateUserRepository;
        this.motionPictureRepository = motionPictureRepository;
    }

    @Override
    public ReelRateUserDto getById(UUID userId) {
        ReelRateUser user = reelRateUserRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        return ReelRateUserMapper.map(user);
    }

    @Override
    public UserRatingDto rate(UUID userId, Long motionPictureId, Rating rating) {
        UserRatingId userRatingId = new UserRatingId(userId, motionPictureId);

        if (!reelRateUserRepository.existsById(userId) || !motionPictureRepository.existsById(motionPictureId)) {
            throw new EntityNotFoundException(userRatingId);
        }

        UserRating userRating = userRatingRepository.findById(userRatingId).orElse(
                new UserRating(userId, motionPictureId, rating)
        );

        userRating.setRating(rating);
        userRating = userRatingRepository.saveAndFlush(userRating);

        return UserRatingMapper.map(userRating);
    }

    @Override
    public List<UserRatingDto> getRatings(UUID userId) {
        return getById(userId).ratings();
    }

    @Override
    public List<MotionPictureDto> getWatchLater(UUID userId) {
        return getById(userId).watchLater();
    }
}
