package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);


    }

    @Override
    public Set<Role> getRolesByIds(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Role> roles = new HashSet<>();
        for (Long roleId : roleIds) {
            roles.add(roleRepository.findById(roleId));
        }
        return roles;
    }
}




