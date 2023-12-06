package ru.kslacker.reelrate.dataaccess.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRatingId implements Serializable {
    private UUID userId;
    private Long motionPictureId;
}
