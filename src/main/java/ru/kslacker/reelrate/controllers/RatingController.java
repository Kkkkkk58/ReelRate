package ru.kslacker.reelrate.controllers;

import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureRatingDto;
import ru.kslacker.reelrate.service.api.RatingService;

@Controller
@GraphQLApi
@PreAuthorize("isAuthenticated()")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GraphQLQuery
    public MotionPictureRatingDto motionPictureRating(@GraphQLNonNull Long motionPictureId) {
        return ratingService.getRating(motionPictureId);
    }
}
