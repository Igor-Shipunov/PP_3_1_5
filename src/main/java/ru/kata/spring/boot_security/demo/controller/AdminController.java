package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String mainPage(Model model, @CurrentSecurityContext(expression = "authentication.principal") User user) {
        /*model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("userInfo", user);
        model.addAttribute("roles", roleService.getAllRoles());*/
        return "mainAdminPage";
    }

    /*@GetMapping(value = "/new")
    public String addNewUser(Model model, @CurrentSecurityContext(expression = "authentication.principal") User user) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userInfo", user);
        return "newUser";
    }*/

    /*@PostMapping()
    public String createNewUser(@ModelAttribute("user") User user, @ModelAttribute("roles") List<Role> roles) {
        userService.saveNewUser(user);
        roleService.saveUserRoles(roles);
        return "redirect:/admin";
    }*/

    /*@GetMapping(value = "/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }

    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user, @ModelAttribute("roles") List<Role> role) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/admin";
    }*/
}
