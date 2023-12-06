package ru.kslacker.reelrate.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import ru.kslacker.reelrate.dataaccess.entities.Cartoon;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.entities.Series;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.mapping.motionpicture.MotionPictureMapper;
import ru.kslacker.reelrate.service.api.DtoMappingMotionPictureVisitor;

@Component
public class DtoMappingMotionPictureVisitorImpl implements DtoMappingMotionPictureVisitor {
    private List<MotionPictureDto> motionPictureDtos = new ArrayList<>();
    @Override
    public void visit(MotionPicture motionPicture) {
        motionPicture.accept(this);
    }

    @Override
    public void visit(Movie movie) {
        motionPictureDtos.add(MotionPictureMapper.map(movie));
    }

    @Override
    public void visit(Series series) {
        motionPictureDtos.add(MotionPictureMapper.map(series));
    }

    @Override
    public void visit(Cartoon cartoon) {
        motionPictureDtos.add(MotionPictureMapper.map(cartoon));
    }

    @Override
    public List<MotionPictureDto> collect() {
        return motionPictureDtos;
    }

    @Override
    public void clear() {
        this.motionPictureDtos = new ArrayList<>();
    }
}
