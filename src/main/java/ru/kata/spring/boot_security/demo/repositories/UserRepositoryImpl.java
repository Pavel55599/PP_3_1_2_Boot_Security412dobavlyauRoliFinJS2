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
        try {//сразу вернул
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    // добавил LEFT JOIN FETCH
    @Override
    public User findById(Long id) {
        System.err.println("findById");
        try {//сразу вернул результат
            return entityManager.createQuery(
                            "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id",
                            User.class)
                    .setParameter("id", id)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
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
        System.err.println("findAll");
        return entityManager.createQuery(//сразу вернул
                "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles",
                User.class).getResultList();
    }
}
