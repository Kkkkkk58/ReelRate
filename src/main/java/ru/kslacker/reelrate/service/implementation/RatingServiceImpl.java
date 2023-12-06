package ru.kslacker.reelrate.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kslacker.reelrate.dataaccess.entities.MotionPictureRatingView;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRatingRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.mapping.motionpicture.MotionPictureRatingMapper;
import ru.kslacker.reelrate.service.api.RatingService;

@Service
@Transactional(readOnly = true)
public class RatingServiceImpl implements RatingService {
    private final MotionPictureRatingRepository motionPictureRatingRepository;

    public RatingServiceImpl(MotionPictureRatingRepository motionPictureRatingRepository) {
        this.motionPictureRatingRepository = motionPictureRatingRepository;
    }

    @Override
    public MotionPictureRatingDto getRating(Long motionPictureId) {
        MotionPictureRatingView motionPictureRating = motionPictureRatingRepository
                .findByMotionPictureId(motionPictureId)
                .orElseThrow(() -> new EntityNotFoundException(motionPictureId));

        return MotionPictureRatingMapper.map(motionPictureRating);
    }

    @Override
    @Transactional
    public MotionPictureRatingDto updateRating(MotionPictureRatingDto motionPictureRating) {
        MotionPictureRatingView motionPictureRatingView = motionPictureRatingRepository
                .findByMotionPictureId(motionPictureRating.motionPictureId())
                .orElse(getNewMotionPictureRatingView(motionPictureRating));

        motionPictureRatingView.setAverageScore(motionPictureRatingView.getAverageScore());
        motionPictureRatingView.setNumberOfVotes(motionPictureRating.numberOfVotes());

        motionPictureRatingView = motionPictureRatingRepository.saveAndFlush(motionPictureRatingView);
        return MotionPictureRatingMapper.map(motionPictureRatingView);
    }

    private MotionPictureRatingView getNewMotionPictureRatingView(MotionPictureRatingDto motionPictureRating) {
        MotionPictureRatingView motionPictureRatingView = new MotionPictureRatingView();
        motionPictureRatingView.setMotionPictureId(motionPictureRating.motionPictureId());

        return motionPictureRatingView;
    }
}
