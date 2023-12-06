package ru.kslacker.reelrate.service.implementation;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kslacker.reelrate.dataaccess.entities.ReelRateUser;
import ru.kslacker.reelrate.dataaccess.entities.User;
import ru.kslacker.reelrate.dataaccess.enums.UserRole;
import ru.kslacker.reelrate.dataaccess.repositories.ReelRateUserRepository;
import ru.kslacker.reelrate.dataaccess.repositories.UserRepository;
import ru.kslacker.reelrate.dto.user.Credentials;
import ru.kslacker.reelrate.exceptions.UserException;
import ru.kslacker.reelrate.service.api.UserService;
import ru.kslacker.reelrate.validation.service.api.ValidationService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ReelRateUserRepository reelRateUserRepository;
    private final ValidationService validator;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            ReelRateUserRepository reelRateUserRepository,
            ValidationService validator,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.reelRateUserRepository = reelRateUserRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void create(Credentials credentials, Set<UserRole> roles) {
        validateCredentials(credentials);

        ReelRateUser reelRateUser = reelRateUserRepository.saveAndFlush(new ReelRateUser());
        User user = User.builder()
                .withUsername(credentials.username())
                .withPassword(passwordEncoder.encode(credentials.password()))
                .withReelRateUser(reelRateUser)
                .withEmail(credentials.email())
                .build();

        userRepository.saveAndFlush(user);
    }

    @Override
    public void create(Credentials credentials) {
        create(credentials, Set.of(UserRole.USER));
    }

    private void validateCredentials(Credentials credentials) {
        validator.validate(credentials);

        if (userRepository.existsByEmail(credentials.email())) {
            throw UserException.emailIsOccupied(credentials.email());
        }
        if (userRepository.existsByUsername(credentials.username())) {
            throw UserException.usernameIsOccupied(credentials.username());
        }
    }
}
