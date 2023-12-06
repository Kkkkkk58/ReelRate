package ru.kslacker.reelrate.controllers;

import java.util.List;
import java.util.UUID;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Controller;
import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;
import ru.kslacker.reelrate.service.api.ReelRateUserService;

@Controller
@GraphQLApi
public class ReelRateUserController {
    private final ReelRateUserService reelRateUserService;

    public ReelRateUserController(ReelRateUserService reelRateUserService) {
        this.reelRateUserService = reelRateUserService;
    }

    @GraphQLQuery
    public ReelRateUserDto reelRateUserById(@GraphQLArgument UUID id) {
        return reelRateUserService.getById(id);
    }

    @GraphQLMutation
    public UserRatingDto rate(
            @GraphQLArgument UUID userId,
            @GraphQLArgument Long motionPictureId,
            @GraphQLArgument Rating rating) {
        return reelRateUserService.rate(userId, motionPictureId, rating);
    }

    @GraphQLQuery
    public List<UserRatingDto> userRates(@GraphQLArgument UUID userId) {
        return reelRateUserService.getRatings(userId);
    }

    @GraphQLMutation
    public List<MotionPictureDto> userWatchLater(@GraphQLArgument UUID userId) {
        return reelRateUserService.getWatchLater(userId);
    }

}
