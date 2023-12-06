package ru.kslacker.reelrate.mapping.reelrateuser;

import ru.kslacker.reelrate.dataaccess.entities.UserRating;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;

public class UserRatingMapper {
    public static UserRatingDto map(UserRating userRating) {
        return UserRatingDto.builder()
                .reelRateUserId(userRating.getUser().getId())
                .motionPictureId(userRating.getMotionPicture().getId())
                .rating(userRating.getRating())
                .build();

    }
}
