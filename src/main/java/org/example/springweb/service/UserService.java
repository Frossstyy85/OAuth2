package org.example.springweb.service;

import jakarta.transaction.Transactional;
import org.example.springweb.entity.Authority;
import org.example.springweb.entity.Role;
import org.example.springweb.entity.User;
import org.example.springweb.repository.RoleRepository;
import org.example.springweb.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Authentication authentication;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, Authentication authentication) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authentication = authentication;
    }

    public Collection<? extends GrantedAuthority> getGrantedAuthorities(User user) {
        return user.getRoles()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Transactional
    public User saveUser(User user) {
        assignRole(user);
        return userRepository.save(user);
    }

    private void assignRole(User user) {
        Role role = roleRepository.findByAuthority(Authority.ADMIN);
        user.getRoles().add(role);
    }

    public User loadCurrentUser() {
        if (!isAuthenticated()) throw new RuntimeException("Not authenticated");
        return ((UserProvider) authentication.getPrincipal()).getUser();
    }

    public boolean isAuthenticated() {
        return (authentication != null
                && authentication.getPrincipal() instanceof UserProvider
                && authentication.isAuthenticated());
    }

}