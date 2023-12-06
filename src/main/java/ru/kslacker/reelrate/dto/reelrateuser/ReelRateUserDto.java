package ru.kslacker.reelrate.dto.reelrateuser;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;

@Builder
public record ReelRateUserDto(UUID id, List<UserRatingDto> ratings, List<MotionPictureDto> watchLater) {

}
