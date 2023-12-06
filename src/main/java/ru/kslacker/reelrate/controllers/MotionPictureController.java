package ru.kslacker.reelrate.controllers;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Controller;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.service.api.MotionPictureService;

@Controller
@GraphQLApi
public class MotionPictureController {
    private final MotionPictureService motionPictureService;

    public MotionPictureController(MotionPictureService motionPictureService) {
        this.motionPictureService = motionPictureService;
    }

    @GraphQLQuery
    public MotionPictureDto motionPictureById(@GraphQLArgument Long id) {
        return motionPictureService.getById(id);
    }
}
