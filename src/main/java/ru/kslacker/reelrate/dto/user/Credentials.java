package ru.kslacker.reelrate.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import ru.kslacker.reelrate.validation.annotations.Password;

@Builder
public record Credentials(@NotBlank String username,
                          @Email String email,
                          @Password String password) {

}
