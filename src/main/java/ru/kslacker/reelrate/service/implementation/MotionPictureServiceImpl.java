package ru.kslacker.reelrate.service.implementation;

import java.util.HashSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kslacker.reelrate.dataaccess.entities.Cartoon;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.entities.Series;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureCreateDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureFiltersDto;
import ru.kslacker.reelrate.dto.motionpicture.SeriesCreateDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.mapping.motionpicture.MotionPictureMapper;
import ru.kslacker.reelrate.service.api.DtoMappingMotionPictureVisitor;
import ru.kslacker.reelrate.service.api.MotionPictureService;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.kslacker.reelrate.dataaccess.specifications.MotionPictureSpecifications.withGenre;
import static ru.kslacker.reelrate.dataaccess.specifications.MotionPictureSpecifications.withTitle;
import static ru.kslacker.reelrate.dataaccess.specifications.MotionPictureSpecifications.withType;

@Service
@Transactional(readOnly = true)
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
        MotionPicture motionPicture = motionPictureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        motionPicture.accept(visitor);
        MotionPictureDto motionPictureDto = visitor.collect().get(0);
        visitor.clear();

        return motionPictureDto;
    }

    @Override
    @Transactional
    public MotionPictureDto addMotionPicture(MotionPictureCreateDto motionPictureCreateDto) {
        MotionPicture motionPicture = createMotionPicture(motionPictureCreateDto);
        motionPicture = motionPictureRepository.saveAndFlush(motionPicture);

        return MotionPictureMapper.map(motionPicture);
    }

    @Override
    @Transactional
    public void deleteById(Long motionPictureId) {
        motionPictureRepository.deleteById(motionPictureId);
    }

    @Override
    public Page<MotionPictureDto> findAll(
            MotionPictureFiltersDto filterRequest,
            int page,
            int size) {

        Specification<MotionPicture> specification = buildSpecification(filterRequest);
        Page<MotionPicture> motionPicturesPaged = motionPictureRepository.findAll(specification, PageRequest.of(page, size));
        return motionPicturesPaged.map(MotionPictureMapper::map);
    }

    private Specification<MotionPicture> buildSpecification(MotionPictureFiltersDto filterRequest) {
        return where(withType(filterRequest.type()))
                .and(withTitle(filterRequest.title()))
                .and(withGenre(filterRequest.genre()));
    }

    private MotionPicture createMotionPicture(MotionPictureCreateDto motionPictureCreateDto) {
        return switch (motionPictureCreateDto.getType()) {
            case MOVIE -> createMovie(motionPictureCreateDto);
            case SERIES -> createSeries(motionPictureCreateDto);
            case CARTOON -> createCartoon(motionPictureCreateDto);
        };
    }

    private Cartoon createCartoon(MotionPictureCreateDto motionPictureCreateDto) {
        return new Cartoon(
                motionPictureCreateDto.getTitle(),
                motionPictureCreateDto.getDescription(),
                new HashSet<>(motionPictureCreateDto.getGenres()),
                new HashSet<>(),
                new HashSet<>(),
                motionPictureCreateDto.getDurationMinutes(),
                motionPictureCreateDto.getReleaseDate());
    }

    private Series createSeries(MotionPictureCreateDto motionPictureCreateDto) {
        return new Series(
                motionPictureCreateDto.getTitle(),
                motionPictureCreateDto.getDescription(),
                new HashSet<>(motionPictureCreateDto.getGenres()),
                new HashSet<>(),
                new HashSet<>(),
                motionPictureCreateDto.getDurationMinutes(),
                motionPictureCreateDto.getReleaseDate(),
                ((SeriesCreateDto) motionPictureCreateDto).getSeason());
    }

    private Movie createMovie(MotionPictureCreateDto motionPictureCreateDto) {
        return new Movie(
                motionPictureCreateDto.getTitle(),
                motionPictureCreateDto.getDescription(),
                new HashSet<>(motionPictureCreateDto.getGenres()),
                new HashSet<>(),
                new HashSet<>(),
                motionPictureCreateDto.getDurationMinutes(),
                motionPictureCreateDto.getReleaseDate());
    }
}
