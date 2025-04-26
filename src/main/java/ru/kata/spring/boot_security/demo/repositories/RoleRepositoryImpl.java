package ru.kata.spring.boot_security.demo.repositories;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


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
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }


    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }


    @Override
    public Role findByName(String name) {
        List<Role> allRoles = findAll();

        for (Role role : allRoles) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        return null;
    }

}