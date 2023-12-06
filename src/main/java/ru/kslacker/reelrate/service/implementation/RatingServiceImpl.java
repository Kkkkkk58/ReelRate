package ru.kslacker.reelrate.service.implementation;

import org.springframework.stereotype.Service;
import ru.kslacker.reelrate.dataaccess.entities.MotionPictureRatingView;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.mapping.motionpicture.MotionPictureRatingMapper;
import ru.kslacker.reelrate.service.api.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
    private final MotionPictureRatingRepository motionPictureRatingRepository;

    public RatingServiceImpl(MotionPictureRatingRepository motionPictureRatingRepository) {
        this.motionPictureRatingRepository = motionPictureRatingRepository;
    }

    @Override
    public MotionPictureRatingDto getRating(Long motionPictureId) {
        MotionPictureRatingView motionPictureRating = motionPictureRatingRepository.findByMotionPictureId(motionPictureId)
                .orElseThrow();

        return MotionPictureRatingMapper.map(motionPictureRating);
    }
}
