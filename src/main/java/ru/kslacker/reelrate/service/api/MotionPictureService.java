package ru.kslacker.reelrate.service.api;

import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;

public interface MotionPictureService {
    MotionPictureDto getById(Long id);
}
