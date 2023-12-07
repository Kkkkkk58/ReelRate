package ru.kslacker.reelrate.service.api;

import ru.kslacker.reelrate.dto.motionpicture.MotionPictureCreateDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;

public interface MotionPictureService {
    MotionPictureDto getById(Long id);

    MotionPictureDto addMotionPicture(MotionPictureCreateDto motionPictureCreateDto);

    void deleteById(Long motionPictureId);
}
