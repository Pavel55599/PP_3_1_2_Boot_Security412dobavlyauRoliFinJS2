package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

//@Controller
//@RequestMapping("/user")
//public class UserController {
//    private final UserService userService;
//
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public ModelAndView userPanel(Principal principal) {
//        ModelAndView mav = new ModelAndView("user/showuser");//просто обьявил , в нем сейчас нет ничего
//        User currentUser = userService.findByUsername(principal.getName());//нахожу нужные данные добавил метод для поиска в БД
//        mav.addObject("user", currentUser);//я нашел нужные данные и добавил их в mav(считай что добавил в "user/showuser") , он у меня теперь не пустой а заполненный
//        return mav;// вернул заполненный mav
//    }
//
//}



@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> allUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")  // Изменил {id} на {userId} чтобы соответствовать параметру
    public User getUserById(@PathVariable Long userId) {  // Изменил имя параметра на userId
        return userService.findById(userId);
    }
}







//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//    private UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @GetMapping
//    public List<User> allUsers() {
//        List<User> users = userService.findAll();
//        return users;
//    }
//
//
//    @GetMapping("/{id}")
//    public User getUserByUsername(@PathVariable Long userId) {
//        User user = userService.findById(userId);
//        return user;
//    }
//}


//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/current")
//    public ResponseEntity<User> getCurrentUser(Principal principal) {
//        User currentUser = userService.findByUsername(principal.getName());
//        return ResponseEntity.ok(currentUser);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.findById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.findAll();
//        return ResponseEntity.ok(users);
//    }
//}





