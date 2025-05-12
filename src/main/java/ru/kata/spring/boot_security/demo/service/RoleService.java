package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface RoleService {

    Role save(Role role);

    List<Role> getAllRoles();

    Optional<Role> findByName(String name);

    Set<Role> getRolesByIds(Set<Long> roleIds);
}

