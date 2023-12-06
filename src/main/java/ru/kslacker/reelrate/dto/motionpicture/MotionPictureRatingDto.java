package ru.kslacker.reelrate.dto.motionpicture;

import lombok.Builder;

@Builder
public record MotionPictureRatingDto(Long motionPictureId, Double rating, Integer numberOfVotes) {

}