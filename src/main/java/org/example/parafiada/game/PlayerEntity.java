package org.example.parafiada.game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.parafiada.football.Category;


@Entity
@Getter
@Setter
@Table(name="gracze")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,columnDefinition = "integer default 0")
    private int points=0;

    @Column()
    private int teamId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

}
