package ru.kslacker.reelrate.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;
import ru.kslacker.reelrate.service.api.RatingStatsService;

@Service
public class RatingStatsServiceImpl implements RatingStatsService {
    @Override
    public MotionPictureRatingDto calculateRatingStats(Long motionPictureId, List<UserRatingDto> motionPictureRates) {
        return MotionPictureRatingDto.builder()
                .motionPictureId(motionPictureId)
                .numberOfVotes(motionPictureRates.size())
                .rating(motionPictureRates.stream().mapToDouble(d -> d.rating().value()).average().orElse(0.0))
                .build();
    }
}
