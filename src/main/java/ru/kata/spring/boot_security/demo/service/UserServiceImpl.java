package ru.kata.spring.boot_security.demo.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Lazy
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        this.roleService = roleService;

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found ", username));
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        if (user.getPassword() == null || !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);


    }


    @Override
    public void saveUserWithRoles(User user, Set<Long> roleIds) {
        if (user.getPassword() == null || !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.setEnabled(user.isEnabled());

        if (roleIds != null && !roleIds.isEmpty()) {
            Set<Role> roles = roleIds.stream()
                    .map(roleId -> {
                        Role role = roleService.getRoleById(roleId);
                        return role;
                    })
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        userRepository.save(user);
    }


    @Override
    public User findById(Long id) {
        return userRepository.findById(id);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public void update(Long id, User user) {

        if (!user.getPassword().equals(userRepository.findById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);


    }


    @Override
    public void updateUserWithRoles(Long id, User updatedUser, List<Long> roleIds) {

        User existingUser = userRepository.findById(id);
        if (existingUser == null) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEnabled(updatedUser.isEnabled());

        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isEmpty()
                && !updatedUser.getPassword().equals(existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        if (roleIds != null) {
            Set<Role> managedRoles = new HashSet<>();
            for (Long roleId : roleIds) {
                Role role = roleService.getRoleById(roleId);
                if (role == null) {
                    throw new EntityNotFoundException("Role not found with id: " + roleId);
                }
                managedRoles.add(role);
            }
            existingUser.setRoles(managedRoles);
        }

        userRepository.save(existingUser);
    }


    @Override
    public void delete(Long id) {
        userRepository.delete(id);

    }

}





