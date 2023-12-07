package ru.kslacker.reelrate.security;

import ru.kslacker.reelrate.dto.user.Credentials;

public interface AuthService {
    String getToken(Credentials credentials);
}
