package ru.kslacker.reelrate.mapping.motionpicture;

import ru.kslacker.reelrate.dataaccess.entities.MotionPictureRatingView;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;

public class MotionPictureRatingMapper {
    public static MotionPictureRatingDto map(MotionPictureRatingView motionPictureRating) {
        return MotionPictureRatingDto.builder()
                .motionPictureId(motionPictureRating.getMotionPictureId())
                .rating(motionPictureRating.getAverageScore())
                .numberOfVotes(motionPictureRating.getNumberOfVotes())
                .build();
    }
}
