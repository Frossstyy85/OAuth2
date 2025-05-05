package org.example.springweb.utils;

import org.example.springweb.entity.Authority;
import org.example.springweb.entity.Role;
import org.example.springweb.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class database implements CommandLineRunner {


    private final RoleRepository roleRepository;

    public database(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        roleRepository.save(new Role(Authority.ADMIN));
    }
}
