package ru.kata.spring.boot_security.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.findAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/viewUser")
    public ResponseEntity<User> showUser(Authentication auth) {
        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roleService.findAll(id), HttpStatus.OK);
    }
}


//@Controller
//public class AdminController {
//
//    private final UserService userService;
//    private final RoleService roleService;
//
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//    @GetMapping("/admin")
//    public String adminInfo( Model model, @ModelAttribute("newUser") User user) {
//        model.addAttribute("users",  userService.findAll());
//        model.addAttribute("roles", roleService.findAll());
//        Thread f = new Thread();f.stop();
//
//        return "/admin";
//    }
//
//    @PostMapping("/admin/addUser")
//    public String addUser(@ModelAttribute("newUser") User user,
//                          @RequestParam("roles") Set<Role> roles) {
//        user.setRoleSet(roles);
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/admin/deleteUser/{id}")
//    public String delete(@PathVariable("id") long id) {
//        userService.deleteById(id);
//        return "redirect:/admin";
//    }
//
//    @PostMapping("/admin/updateUser/{id}")
//    public String updateUser(User user, @RequestParam("roles") Set<Role> roles) {
//        user.setRoleSet(roles);
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//}