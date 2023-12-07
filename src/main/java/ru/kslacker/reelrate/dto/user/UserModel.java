package ru.kslacker.reelrate.dto.user;

import jakarta.validation.Valid;

public record UserModel(@Valid Credentials credentials) {

}
