
package ru.kata.spring.boot_security.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.model.User;

@Service
public interface UserService extends UserDetailsService {
    User findById(Long id);
    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);
    User findByUsername(String username);
    void updateUser(User user,long id);
    User findByEmail(String email);
}

