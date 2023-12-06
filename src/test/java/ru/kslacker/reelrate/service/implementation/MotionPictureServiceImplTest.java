package ru.kslacker.reelrate.service.implementation;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Movie;
import ru.kslacker.reelrate.dataaccess.repositories.MotionPictureRepository;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.exceptions.EntityNotFoundException;
import ru.kslacker.reelrate.service.api.MotionPictureService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MotionPictureServiceImplTest {
    @Mock
    private MotionPictureRepository motionPictureRepository;
    private MotionPictureService motionPictureService;

    @BeforeEach
    void setUp() {
        this.motionPictureService = new MotionPictureServiceImpl(
                motionPictureRepository,
                new DtoMappingMotionPictureVisitorImpl());
    }

    @Test
    void getById_existingMotionPicture_isOkReturnsMotionPicture() {
        MotionPicture motionPicture = getTestMotionPicture();
        Long id = motionPicture.getId();
        when(motionPictureRepository.findById(id)).thenReturn(Optional.of(motionPicture));

        MotionPictureDto motionPictureDto = motionPictureService.getById(id);

        assertThat(motionPictureDto).usingRecursiveComparison().isEqualTo(motionPicture);
        verify(motionPictureRepository, times(1)).findById(id);
    }

    @Test
    void getById_nonExistentEntity_throws() {
        Long id = 1L;
        when(motionPictureRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> motionPictureService.getById(id));
        verify(motionPictureRepository, times(1)).findById(id);
    }

    private MotionPicture getTestMotionPicture() {
        Movie movie = new Movie(
                "Test",
                "Test",
                Set.of(),
                Set.of(),
                Set.of(),
                1,
                LocalDate.now()
        );
        movie.setId(1L);

        return movie;
    }
}
