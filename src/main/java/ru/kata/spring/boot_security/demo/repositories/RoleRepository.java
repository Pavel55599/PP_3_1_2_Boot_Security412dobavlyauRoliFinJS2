package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {
    Role save(Role role);

    Role findById(Long id);

    List<Role> findAll();

    Role findByName(String name);
}
