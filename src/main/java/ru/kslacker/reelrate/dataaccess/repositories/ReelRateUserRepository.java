package ru.kslacker.reelrate.dataaccess.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;

@Repository
public interface ReelRateUserRepository extends JpaRepository<ReelRateUser, UUID> {
}
