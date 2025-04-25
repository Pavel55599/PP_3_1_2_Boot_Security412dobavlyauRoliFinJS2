package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;


@Repository
public interface RoleRepository {
    Role save(Role role);

    Role findById(Long id);

    List<Role> findAll();

    Role findByName(String name);
}
