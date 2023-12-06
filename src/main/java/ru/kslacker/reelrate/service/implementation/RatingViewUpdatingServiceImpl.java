package ru.kslacker.reelrate.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.UserRating;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dataaccess.repositories.UserRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.mapping.reelrateuser.UserRatingMapper;
import ru.kslacker.reelrate.service.api.RatingService;
import ru.kslacker.reelrate.service.api.RatingStatsService;
import ru.kslacker.reelrate.service.api.RatingViewUpdatingService;

@Service
@Transactional(readOnly = true)
public class RatingViewUpdatingServiceImpl implements RatingViewUpdatingService {
    private final MotionPictureRepository motionPictureRepository;
    private final UserRatingRepository userRatingRepository;
    private final RatingStatsService ratingStatsService;
    private final RatingService ratingService;

    public RatingViewUpdatingServiceImpl(
            MotionPictureRepository motionPictureRepository,
            UserRatingRepository userRatingRepository,
            RatingStatsService ratingStatsService,
            RatingService ratingService) {

        this.motionPictureRepository = motionPictureRepository;
        this.userRatingRepository = userRatingRepository;
        this.ratingStatsService = ratingStatsService;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public void updateRatingView() {
        // TODO multithreading
        for (MotionPicture motionPicture : motionPictureRepository.findAll()) {
            Long motionPictureId = motionPicture.getId();
            List<UserRating> userRatings = userRatingRepository.findAllByMotionPictureId(motionPictureId);
            MotionPictureRatingDto motionPictureRatingDto = ratingStatsService.calculateRatingStats(
                    motionPictureId,
                    userRatings.stream().map(UserRatingMapper::map).toList()
            );
            ratingService.updateRating(motionPictureRatingDto);
        }
    }
}
