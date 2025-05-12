package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role save(Role role) {
        entityManager.persist(role);
        return role;

    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Role.class, id));
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Optional<Role> findByName(String name) {
        List<Role> roles = findAll();
        for (Role role : roles) {
            if (role.getName().equals(name)) {
                return Optional.of(role);
            }
        }
        return Optional.empty();
    }
}
