package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Repository
public interface UserRepository {
    User findByUsername(String username);

    User findById(Long id);

    void save(User user);

    void update(User user);

    void delete(Long id);

    List<User> findAll();
}