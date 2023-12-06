package ru.kslacker.reelrate.dto.reelrateuser;

import java.util.UUID;

import lombok.Builder;
import ru.kslacker.reelrate.dataaccess.models.Rating;

@Builder
public record UserRatingDto(UUID reelRateUserId, Long motionPictureId, Rating rating) {

}