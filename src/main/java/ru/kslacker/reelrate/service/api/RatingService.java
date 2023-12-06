package ru.kslacker.reelrate.service.api;

import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;

public interface RatingService {
    MotionPictureRatingDto getRating(Long motionPictureId);
    MotionPictureRatingDto updateRating(MotionPictureRatingDto motionPictureRating);
}
