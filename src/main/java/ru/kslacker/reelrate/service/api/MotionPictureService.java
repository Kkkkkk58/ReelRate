package ru.kslacker.reelrate.service.api;

import org.springframework.data.domain.Page;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureCreateDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureFiltersDto;

public interface MotionPictureService {
    MotionPictureDto getById(Long id);

    MotionPictureDto addMotionPicture(MotionPictureCreateDto motionPictureCreateDto);

    void deleteById(Long motionPictureId);

    Page<MotionPictureDto> findAll(MotionPictureFiltersDto filterRequest, int page, int size);
}
