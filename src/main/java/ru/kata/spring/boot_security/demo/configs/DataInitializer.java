package ru.kata.spring.boot_security.demo.configs;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Component
@Transactional
public class DataInitializer {


    @Bean
    public CommandLineRunner initUsers(UserService userService,
                                       PasswordEncoder passwordEncoder,
                                       RoleService roleService) {
        return args -> {


            Role adminRole = createRoleIfNotExists("ROLE_ADMIN", roleService);
            Role userRole = createRoleIfNotExists("ROLE_USER", roleService);


            createUserIfNotExists(
                    "admin",
                    "admin",
                    "Adminov",
                    Set.of(adminRole, userRole),
                    userService,
                    passwordEncoder);


            createUserIfNotExists(
                    "user",
                    "user",
                    "Userov",
                    Set.of(userRole),
                    userService,
                    passwordEncoder
            );
        };
    }

    private Role createRoleIfNotExists(String roleName, RoleService roleService) {
        Role role = roleService.findByName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleService.save(role);
        }
        return role;
    }

    private void createUserIfNotExists(String username,
                                       String password,
                                       String lastName,
                                       Set<Role> roles,
                                       UserService userService,
                                       PasswordEncoder passwordEncoder) {
        if (userService.findByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setLastName(lastName);
            user.setEnabled(true);
            user.setRoles(roles);
            userService.save(user);
        }
    }

}






