package ru.kslacker.reelrate.service.api;

import java.util.Set;

import ru.kslacker.reelrate.dataaccess.enums.UserRole;
import ru.kslacker.reelrate.dto.user.Credentials;

public interface UserService {
    void create(Credentials credentials, Set<UserRole> roles);
    void create(Credentials credentials);
}
