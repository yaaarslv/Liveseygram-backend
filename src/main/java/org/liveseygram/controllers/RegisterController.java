package org.liveseygram.controllers;

import org.liveseygram.entities.User;
import org.liveseygram.models.RegisterResult;
import org.liveseygram.models.SuccessfulRegisterData;
import org.liveseygram.models.UnsuccessfulRegisterData;
import org.liveseygram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class RegisterController {
    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public RegisterResult createUser(@RequestBody User user) {
        if (!user.isCorrect()) {
            return new UnsuccessfulRegisterData("Переданы не все данные!", false);
        }
        Optional<User> user1 = userService.findFirstByEmail(user.getEmail());
        Optional<User> user2 = userService.findFirstByUsername(user.getUsername());
        if (user1.isPresent()){
            return new UnsuccessfulRegisterData("Пользователь с такой почтой уже существует!", false);
        } else if (user2.isPresent()){
            return new UnsuccessfulRegisterData("Пользователь с таким username уже существует!", false);
        } else {
            user.setFullName(user.getFirstName() + " " + user.getLastName());
            user.setAvatarPath("http://localhost:8080/avatar/default_avatar.webp");
            User createdUser = userService.save(user);
            return new SuccessfulRegisterData(createdUser, true);
        }
    }
}
