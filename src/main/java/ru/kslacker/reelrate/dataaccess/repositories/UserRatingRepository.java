package ru.kslacker.reelrate.dataaccess.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kslacker.reelrate.dataaccess.entities.UserRating;
import ru.kslacker.reelrate.dataaccess.models.UserRatingId;

@Repository
public interface UserRatingRepository extends JpaRepository<UserRating, UserRatingId> {
    List<UserRating> findAllByUserId(UUID userId);
    List<UserRating> findAllByMotionPictureId(Long motionPictureId);
}
