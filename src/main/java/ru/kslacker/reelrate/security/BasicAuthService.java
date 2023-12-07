package ru.kslacker.reelrate.security;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import ru.kslacker.reelrate.dto.user.Credentials;

@Service
public class BasicAuthService implements AuthService {
    @Override
    public String getToken(Credentials credentials) {
        String auth = credentials.username() + ":" + credentials.password();
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(), false);
        return "Basic " + new String(encodedAuth);
    }
}
