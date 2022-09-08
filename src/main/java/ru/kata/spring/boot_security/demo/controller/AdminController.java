package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
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
    public String adminInfo( Model model, @ModelAttribute("newUser") User user) {
        model.addAttribute("users",  userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        Thread f = new Thread();
        f.stop();

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

    @PostMapping("/admin/updateUser/{id}")
    public String updateUser(User user, @RequestParam("roles") Set<Role> roles) {
        user.setRoleSet(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}