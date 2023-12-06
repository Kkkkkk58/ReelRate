package ru.kslacker.reelrate.dataaccess.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kslacker.reelrate.dataaccess.entities.MotionPictureRatingView;

@Repository
public interface MotionPictureRatingRepository extends JpaRepository<MotionPictureRatingView, Long> {
    Optional<MotionPictureRatingView> findByMotionPictureId(Long motionPictureId);
}
