package org.example.springweb.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public final class Role  {

    public Role(){}

    public Role(Authority authority){
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Override
    public String toString() {
        return "Role {\n" +
               "    id        = " + id + ",\n" +
               "    authority = " + authority + "\n" +
               "}";
    }


}


