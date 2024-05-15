package org.liveseygram.controllers;

import org.liveseygram.entities.User;
import org.liveseygram.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.liveseygram.services.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/updateFirstName")
    public ResponseEntity<User> updateUserFirstName(@RequestBody UpdatingUserFirstName user) {
        User curUser = userService.findById(user.getId()).get();
        curUser.setFirstName(user.getFirstName());
        curUser.setFullName(curUser.getFirstName() + " " + curUser.getLastName());
        User updatedUser = userService.update(curUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/updateLastName")
    public ResponseEntity<User> updateUserLastName(@RequestBody UpdatingUserLastName user) {
        User curUser = userService.findById(user.getId()).get();
        curUser.setLastName(user.getLastName());
        curUser.setFullName(curUser.getFirstName() + " " + curUser.getLastName());
        User updatedUser = userService.update(curUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<User> updateUserPassword(@RequestBody UpdatingUserPassword user) {
        User curUser = userService.findById(user.getId()).get();
        curUser.setPassword(user.getPassword());
        User updatedUser = userService.update(curUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("/updateUserName")
    public ResponseEntity<UpdatingUsernameResult> updateUserUsername(@RequestBody UpdatingUserUsername user) {
        Optional<User> susUser = userService.findFirstByUsername(user.getUsername());
        if (susUser.isPresent()){
            return ResponseEntity.ok(new UnsuccessfulUpdatingUsername("Данное имя пользователя занято!", false));
        }

        User curUser = userService.findById(user.getId()).get();
        curUser.setUsername(user.getUsername());
        User updatedUser = userService.update(curUser);
        return ResponseEntity.ok(new SuccessfulUpdatingUsername(updatedUser, true));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteByEntity")
    public ResponseEntity<Void> deleteUserByEntity(@RequestBody User user) {
        userService.deleteByEntity(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAll();
        return ResponseEntity.ok().build();
    }
}