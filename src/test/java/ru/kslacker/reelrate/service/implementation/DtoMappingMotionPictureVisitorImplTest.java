package ru.kslacker.reelrate.service.implementation;

import java.time.LocalDate;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;
import ru.kslacker.reelrate.dataaccess.entities.Series;
import ru.kslacker.reelrate.dto.motionpicture.MotionPictureDto;
import ru.kslacker.reelrate.dto.motionpicture.SeriesDto;
import ru.kslacker.reelrate.service.api.DtoMappingMotionPictureVisitor;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DtoMappingMotionPictureVisitorImplTest {
    private final DtoMappingMotionPictureVisitor visitor = new DtoMappingMotionPictureVisitorImpl();

    @BeforeEach
    void setUp() {
        visitor.clear();
    }

    @Test
    void map_mapSeries_isOkReturnsSeriesDto() {
        MotionPicture series = getTestSeries();

        series.accept(visitor);
        MotionPictureDto motionPictureDto = visitor.collect().get(0);

        assertInstanceOf(SeriesDto.class, motionPictureDto);
    }

    @NotNull
    private static MotionPicture getTestSeries() {
        MotionPicture series = new Series(
                "Series",
                "series",
                Set.of(),
                Set.of(),
                Set.of(),
                1,
                LocalDate.now(),
                1
        );
        series.setId(1L);
        return series;
    }

}