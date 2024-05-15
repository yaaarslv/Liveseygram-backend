package org.liveseygram.controllers;

import org.liveseygram.entities.User;
import org.liveseygram.models.LoginResult;
import org.liveseygram.models.PasswordCheckRequest;
import org.liveseygram.models.SuccessfulLoginData;
import org.liveseygram.models.UnsuccessfulLoginData;
import org.liveseygram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResult login(@RequestBody PasswordCheckRequest request) {
        String email = request.getEmail();
        String rawPassword = request.getPassword();
        String additionalParameter = request.getAdditionalParameter();

        if (additionalParameter == null || !additionalParameter.equals("sent from browser") || email == null || rawPassword == null) {
            return new UnsuccessfulLoginData("Переданы не все параметры для авторизации!", false);
        }

        Optional<User> user = userService.findFirstByEmail(email);
        if (user.isEmpty()) {
            return new UnsuccessfulLoginData("Пользователя с такой почтой не существует!", false);
        }

        boolean result = checkPassword(user, rawPassword);
        if (result) {
            return new SuccessfulLoginData(user.get(), true);
        } else {
            return new UnsuccessfulLoginData("Неверный логин или пароль!", false);
        }
    }

    private boolean checkPassword(Optional<User> user, String rawPassword) {
        return user.filter(value -> userService.isPasswordCorrect(rawPassword, value.getPassword())).isPresent();
    }
}
