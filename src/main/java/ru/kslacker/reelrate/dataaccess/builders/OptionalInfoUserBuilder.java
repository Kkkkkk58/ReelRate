package ru.kslacker.reelrate.dataaccess.builders;

import ru.kslacker.reelrate.dataaccess.entities.User;
import ru.kslacker.reelrate.dataaccess.enums.UserRole;

public interface OptionalInfoUserBuilder {
    OptionalInfoUserBuilder withEmail(String email);

    OptionalInfoUserBuilder addRole(UserRole role);

    User build();
}
