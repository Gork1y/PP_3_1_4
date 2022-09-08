package ru.kata.spring.boot_security.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.kata.spring.boot_security.demo.model.Role;
@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    <S extends Role> List<S> saveAll(Iterable<S> entities);

    Role findByName(String name);
}
