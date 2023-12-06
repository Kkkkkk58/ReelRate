package ru.kslacker.reelrate.dataaccess.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;

public interface ReelRateUserRepository extends JpaRepository<ReelRateUser, UUID> {
}
