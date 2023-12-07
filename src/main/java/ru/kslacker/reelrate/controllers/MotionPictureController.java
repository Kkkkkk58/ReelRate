package ru.kslacker.reelrate.controllers;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLNonNull;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureCreateDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureFiltersDto;
import ru.kslacker.reelrate.security.annotations.IsAdmin;
import ru.kslacker.reelrate.service.api.MotionPictureService;

@Controller
@GraphQLApi
@PreAuthorize("isAuthenticated()")
public class MotionPictureController {
    private final MotionPictureService motionPictureService;

    public MotionPictureController(MotionPictureService motionPictureService) {
        this.motionPictureService = motionPictureService;
    }

    @GraphQLQuery
    public MotionPictureDto motionPictureById(@GraphQLNonNull Long id) {
        return motionPictureService.getById(id);
    }

    @GraphQLQuery
    public Page<MotionPictureDto> motionPictures(@GraphQLArgument(defaultValue = "0") @PositiveOrZero int page,
                                                 @GraphQLArgument(defaultValue = "10") @PositiveOrZero int size,
                                                 @GraphQLArgument(defaultValue = "{}") @NotNull MotionPictureFiltersDto filterRequest) {
        return motionPictureService.findAll(filterRequest, page, size);
    }

    @GraphQLMutation
    @IsAdmin
    public MotionPictureDto addMotionPicture(@GraphQLNonNull MotionPictureCreateDto motionPictureCreateDto) {
        return motionPictureService.addMotionPicture(motionPictureCreateDto);
    }

    @GraphQLMutation
    @IsAdmin
    public void deleteMotionPicture(@GraphQLNonNull Long motionPictureId) {
        motionPictureService.deleteById(motionPictureId);
    }
}
