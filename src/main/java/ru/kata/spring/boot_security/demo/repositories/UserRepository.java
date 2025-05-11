package ru.kata.spring.boot_security.demo.repositories;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserRepository {
    User findByUsername(String username);

    User findById(Long id);

    User save(User user);

    void delete(Long id);

    List<User> findAll();
}