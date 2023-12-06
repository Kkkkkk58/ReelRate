package ru.kslacker.reelrate.service.api;

import java.util.List;
import java.util.UUID;

import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;

public interface ReelRateUserService {
    ReelRateUserDto getById(UUID userId);
    UserRatingDto rate(UUID userId, Long motionPictureId, Rating rating);
    List<UserRatingDto> getRatings(UUID userId);
    List<MotionPictureDto> getWatchLater(UUID userId);
}
