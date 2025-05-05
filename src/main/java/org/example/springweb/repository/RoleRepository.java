package org.example.springweb.repository;

import org.example.springweb.entity.Authority;
import org.example.springweb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(Authority authority);

}
