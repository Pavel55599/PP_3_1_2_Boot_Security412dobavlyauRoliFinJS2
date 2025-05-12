package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository {

    Role save(Role role);

    Optional<Role> findById(Long id);

    List<Role> findAll();

    Optional<Role> findByName(String name);
}
