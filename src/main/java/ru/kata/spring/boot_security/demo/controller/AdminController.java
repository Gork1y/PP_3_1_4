package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/admin/addUserForm")
    public String addUserForm(@ModelAttribute("newUser") User user, Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "/admin";
    }

    @PostMapping("/admin/addUser")
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam("roles") Set<Role> roles) {
        user.setRoleSet(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteUser/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userToUpdate", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "/admin";
    }

    @PostMapping("/admin/updateUser")
    public String updateUser(User user, @RequestParam("roles") Set<Role> roles) {
        user.setRoleSet(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}