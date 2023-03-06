package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> currentUser(Authentication authentication) {
        return ResponseEntity.ok((User) authentication.getPrincipal());
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> saveNewUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        System.out.println(user);
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(userService.getUserById(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
