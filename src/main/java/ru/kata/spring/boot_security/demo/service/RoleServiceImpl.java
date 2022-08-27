package ru.kata.spring.boot_security.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Transactional
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }
}
