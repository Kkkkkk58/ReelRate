package ru.kslacker.reelrate.controllers;

import java.util.List;
import java.util.UUID;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import ru.kslacker.reelrate.dataaccess.models.Rating;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.reelrateuser.ReelRateUserDto;
import ru.kslacker.reelrate.dto.reelrateuser.UserRatingDto;
import ru.kslacker.reelrate.security.UserDetailsImpl;
import ru.kslacker.reelrate.service.api.ReelRateUserService;

@Controller
@GraphQLApi
@PreAuthorize("isAuthenticated()")
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
            @GraphQLArgument Long motionPictureId,
            @GraphQLArgument Rating rating) {
        return reelRateUserService.rate(getReelRateUserId(), motionPictureId, rating);
    }

    @GraphQLQuery
    public List<UserRatingDto> userRates() {
        return reelRateUserService.getRatings(getReelRateUserId());
    }

    @GraphQLMutation
    public List<MotionPictureDto> userWatchLater() {
        return reelRateUserService.getWatchLater(getReelRateUserId());
    }

    private UUID getReelRateUserId() {
        UserDetailsImpl principal = getUser();
        return principal.getReelRateUserId();
    }

    private UserDetailsImpl getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) authentication.getPrincipal();
    }

}
