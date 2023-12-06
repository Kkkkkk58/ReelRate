package ru.kslacker.reelrate.mapping.reelrateuser;

import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.mapping.motionpicture.MotionPictureMapper;

public class ReelRateUserMapper {
    public static ReelRateUserDto map(ReelRateUser reelRateUser) {
        return ReelRateUserDto.builder()
                .id(reelRateUser.getId())
                .ratings(reelRateUser.getRatings().stream().map(UserRatingMapper::map).toList())
                .watchLater(reelRateUser.getWatchLater().stream().map(MotionPictureMapper::map).toList())
                .build();
    }
}
