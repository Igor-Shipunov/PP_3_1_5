package ru.kata.spring.boot_security.demo.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class FirstRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public FirstRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> currentUser(@CurrentSecurityContext User user) {
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> userById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/new")
    public void saveNewUser(@RequestBody User user) {
        System.out.println(user.toString());
        userService.saveNewUser(user);
    }

    @PatchMapping("/{id}")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }


    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(userService.getUserById(id));
    }
}
