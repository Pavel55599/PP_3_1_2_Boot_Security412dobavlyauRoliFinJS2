package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin(origins = "http://localhost:8080/api/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestParam(value = "selectedRoles", required = false) Set<Long> selectedRoles) {
        User savedUser = userService.saveUserWithRoles(user, selectedRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user,
            @RequestParam(value = "selectedRoles", required = false) List<Long> roleIds) {
        User updatedUser = userService.updateUserWithRoles(id, user, roleIds);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}


//@RestController
//@RequestMapping("/api/admin")
//public class AdminController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<Void> createUser(
//            @RequestBody User user,
//            @RequestParam(value = "selectedRoles", required = false) Set<Long> selectedRoles) {
//        userService.saveUserWithRoles(user, selectedRoles);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateUser(
//            @PathVariable Long id,
//            @RequestBody User user,
//            @RequestParam(value = "selectedRoles", required = false) List<Long> roleIds) {
//        userService.updateUserWithRoles(id, user, roleIds);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/roles")
//    public Set<Role> getAllRoles() {
//        return (Set<Role>) roleService.getAllRoles();
//    }
//}



//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public ModelAndView adminPanel() {
//        ModelAndView mav = new ModelAndView("admin/indexlist");
//        mav.addObject("users", userService.findAll());
//        return mav;
//    }
//
//    @GetMapping("/show")
//    public ModelAndView showUser(@RequestParam("id") Long id) {
//        ModelAndView mav = new ModelAndView("admin/show");
//        mav.addObject("user", userService.findById(id));
//        return mav;
//    }
//
//    @GetMapping("/new")
//    public ModelAndView newUser() {
//        ModelAndView mav = new ModelAndView("admin/new");
//        mav.addObject("user", new User());
//        mav.addObject("allRoles", roleService.getAllRoles());
//        return mav;
//    }
//
//    @PostMapping
//    public ModelAndView createUser(@ModelAttribute("user") User user,
//                                   @RequestParam(value = "selectedRoles", required = false) Set<Long> selectedRoles) {
//        userService.saveUserWithRoles(user, selectedRoles);
//        return new ModelAndView("redirect:/admin");
//    }
//
//    @GetMapping("/edit")
//    public ModelAndView editUser(@RequestParam("id") Long id) {
//        ModelAndView mav = new ModelAndView("admin/edit");
//        mav.addObject("user", userService.findById(id));
//        mav.addObject("allRoles", roleService.getAllRoles());
//        return mav;
//    }
//
//    @PostMapping("/update")
//    public ModelAndView updateUser(@RequestParam("id") Long id,
//                                   @ModelAttribute("user") User user,
//                                   @RequestParam(value = "selectedRoles", required = false) List<Long> roleIds) {
//        userService.updateUserWithRoles(id, user, roleIds);
//        return new ModelAndView("redirect:/admin");
//    }
//
//    @GetMapping("/delete")
//    public ModelAndView showDeleteForm(@RequestParam("id") Long id) {
//        ModelAndView mav = new ModelAndView("admin/delete");
//        mav.addObject("user", userService.findById(id));
//        return mav;
//    }
//
//    @PostMapping("/delete")
//    public ModelAndView deleteUser(@RequestParam("id") Long id) {
//        userService.delete(id);
//        return new ModelAndView("redirect:/admin");
//    }
//}












//@RestController
//@RequestMapping("/api/admin")
//public class AdminController {
//    private final UserService userService;
//    private final RoleService roleService;
//
//
//    public AdminController(UserService userService, RoleService roleService) {
//        this.userService = userService;
//        this.roleService = roleService;
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
//
//    @GetMapping("/new")
//    public User newUser(@ModelAttribute User user) {
//        User newUser = new User();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(user.getPassword());
//        newUser.setRoles(user.getRoles());
//        userService.save(newUser);
//        return newUser;
//    }
//
//
//    @PostMapping
//    public User CreateUser(User user, Set<Long> roleIds) {
//        User newUser = userService.saveUserWithRoles(user, roleIds);
//        return user;
//
//    }
//}