package ru.kslacker.reelrate.service.api;

import java.util.List;

import ru.kslacker.reelrate.dataaccess.visitors.MotionPictureVisitor;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;

public interface DtoMappingMotionPictureVisitor extends MotionPictureVisitor {
    List<MotionPictureDto> collect();
    void clear();
}
