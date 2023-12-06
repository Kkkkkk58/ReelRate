package ru.kslacker.reelrate.service.api;

import java.util.List;

import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;

public interface RatingStatsService {
    MotionPictureRatingDto calculateRatingStats(Long motionPictureId, List<UserRatingDto> motionPictureRates);
}
