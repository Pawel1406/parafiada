package org.example.parafiada.login;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Uzytkownicy")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    @NotBlank(message = "Nazwa uzytkownika nie moze byc pusta")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Hasło nie moze byc puste")
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


}
