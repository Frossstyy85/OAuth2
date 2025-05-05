package org.example.springweb.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
public class User {

    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String username;

    @Setter
    private String providerId;

    @Setter
    private String provider;

    @Setter
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private Collection<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User {" +
                "\n    id=" + id + "," +
                "\n    username='" + username + "'," +
                "\n    providerId='" + providerId + "'," +
                "\n    provider='" + provider + "'," +
                "\n    picture='" + avatar + "'," +
                "\n    roles=" + roles +
                "\n}";
    }

}