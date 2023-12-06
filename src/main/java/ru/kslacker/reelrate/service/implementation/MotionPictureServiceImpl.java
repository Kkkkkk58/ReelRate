package ru.kslacker.reelrate.service.implementation;

import org.springframework.stereotype.Service;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.service.api.DtoMappingMotionPictureVisitor;
import ru.kslacker.reelrate.service.api.MotionPictureService;

@Service
public class MotionPictureServiceImpl implements MotionPictureService {
    private final MotionPictureRepository motionPictureRepository;
    private final DtoMappingMotionPictureVisitor visitor;

    public MotionPictureServiceImpl(MotionPictureRepository motionPictureRepository,
                                    DtoMappingMotionPictureVisitor visitor) {
        this.motionPictureRepository = motionPictureRepository;
        this.visitor = visitor;
    }

    @Override
    public MotionPictureDto getById(Long id) {
        // TODO
        MotionPicture motionPicture = motionPictureRepository.findById(id).orElseThrow();
        motionPicture.accept(visitor);
        MotionPictureDto motionPictureDto = visitor.collect().get(0);
        visitor.clear();

        return motionPictureDto;
    }
}
