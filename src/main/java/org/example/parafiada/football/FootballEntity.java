package org.example.parafiada.football;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.parafiada.game.PlayerEntity;

@Entity
@Getter
@Setter
@Table(name="FootballTeam")
public class FootballEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Category footballCategory;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(nullable = false)
    private String captain;

    @Column(nullable=false,columnDefinition = "integer default 0")
    private int points=0;

    @Column(nullable=false,columnDefinition = "integer default 0")
    private int goalsScored=0;

    @Column(nullable=false,columnDefinition = "integer default 0")
    private int goalsConceded=0;

    @Column(nullable=false,columnDefinition = "integer default 0")
    private int yellowCard=0;

    @Column(nullable=false,columnDefinition = "integer default 0")
    private int redCard=0;



}
