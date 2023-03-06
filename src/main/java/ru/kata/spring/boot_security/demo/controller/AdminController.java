package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping()
    public String mainPage() {
        return "mainAdminPage";
    }

    @GetMapping(value = "/new")
    public String addNewUser() {
        return "newUserPage";
    }
}
