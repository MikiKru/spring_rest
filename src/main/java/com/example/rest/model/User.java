package com.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
public class User {
    @Id                                             // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    private Long id;
    private String login;
    private String password;
    private LocalDateTime registration_date = LocalDateTime.now();
    private boolean active = false;

    // RELACJA N:M
    @ManyToMany(
            cascade = CascadeType.ALL,                          // pełna rekursywność
            fetch = FetchType.EAGER                             // zachłanne pobieranie rekordów
    )
    @JoinTable(
            name = "user_role",                                 // nazwa tabelki
            joinColumns = @JoinColumn(name = "user_id"),        // klucz uzytkownika
            inverseJoinColumns = @JoinColumn(name = "role_id")  // klucz roli
    )
    private Set<Role> roles = new HashSet<>();

}
