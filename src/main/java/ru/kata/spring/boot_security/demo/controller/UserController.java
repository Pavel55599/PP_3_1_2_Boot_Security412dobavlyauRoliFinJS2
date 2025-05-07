package ru.kata.spring.boot_security.demo.controller;

import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        return ResponseEntity.ok(currentUser);
    }

}





//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.findAll();
//        return ResponseEntity.ok(users);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.findById(id);
//        return ResponseEntity.ok(user);
//    }

    // Другие REST методы (POST, PUT, DELETE) по необходимости




//@RestController
//@RequestMapping("/api/user")
////@CrossOrigin(origins = "http://localhost:8080")
//public class UserController {
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    //@GetMapping
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<User> allUsers() {
//        System.err.println("GET /api/user - Запрос всех пользователей");
//        return userService.findAll();
//    }
//
//    //@GetMapping("/{id}")
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public User getUserById(@PathVariable Long id) {
//        System.err.println("GET /api/user/" + id + " - Запрос пользователя");
//        return userService.findById(id);
//    }
//}















