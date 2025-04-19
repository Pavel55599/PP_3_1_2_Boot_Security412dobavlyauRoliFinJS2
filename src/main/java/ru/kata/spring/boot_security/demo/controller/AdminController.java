package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ModelAndView adminPanel() {
        ModelAndView mav = new ModelAndView("admin/indexlist");
        mav.addObject("users", userService.findAll());
        return mav;
    }

    @GetMapping("/show")
    public ModelAndView showUser(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/show");
        mav.addObject("user", userService.findById(id));
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newUser() {
        ModelAndView mav = new ModelAndView("admin/new");
        mav.addObject("user", new User());
        mav.addObject("allRoles", roleService.getAllRoles());
        return mav;
    }

    @PostMapping
    public ModelAndView createUser(@ModelAttribute("user") User user,
                                   @RequestParam(value = "selectedRoles", required = false) Set<Long> selectedRoles) {
        userService.saveUserWithRoles(user, selectedRoles);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/edit");
        mav.addObject("user", userService.findById(id));
        mav.addObject("allRoles", roleService.getAllRoles());
        return mav;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@RequestParam("id") Long id,
                                   @ModelAttribute("user") User user,
                                   @RequestParam(value = "selectedRoles", required = false) List<Long> roleIds) {
        userService.updateUserWithRoles(id, user, roleIds);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/delete")
    public ModelAndView showDeleteForm(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/delete");
        mav.addObject("user", userService.findById(id));
        return mav;
    }

    @PostMapping("/delete")
    public ModelAndView deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return new ModelAndView("redirect:/admin");
    }
}






