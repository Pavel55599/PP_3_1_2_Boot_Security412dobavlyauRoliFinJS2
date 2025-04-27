package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {

    void save(User user);

    void saveUserWithRoles(User user, Set<Long> roleIds);

    User findById(Long id);

    List<User> findAll();

    void updateUserWithRoles(Long id, User user, List<Long> roleIds);

    void delete(Long id);

    User findByUsername(String username);
}


