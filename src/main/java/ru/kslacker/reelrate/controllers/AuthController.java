package ru.kslacker.reelrate.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kslacker.reelrate.dto.user.UserModel;
import ru.kslacker.reelrate.security.AuthService;
import ru.kslacker.reelrate.service.api.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody UserModel userModel) {
        userService.create(userModel.credentials());
        return authService.getToken(userModel.credentials());
    }
}
