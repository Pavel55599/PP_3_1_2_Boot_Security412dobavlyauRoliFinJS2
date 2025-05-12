package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        return entityManager
                .createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }



    @Override
    public User findById(Long id) {
        return entityManager.createQuery(
                        "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id",
                        User.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }


    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery(
                "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles",
                User.class).getResultList();
    }
}
