package ru.kslacker.reelrate.dataaccess.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.kslacker.reelrate.dataaccess.entities.MotionPicture;

@Repository
public interface MotionPictureRepository extends JpaRepository<MotionPicture, Long>, JpaSpecificationExecutor<MotionPicture> {

}
