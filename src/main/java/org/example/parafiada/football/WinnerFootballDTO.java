package org.example.parafiada.football;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WinnerFootballDTO (
        @NotNull
        Long id,
        @NotNull
        FootballCategory footballCategory,
        @NotNull
        String name,
        @NotNull
        int points,
        @NotNull
        int goalsScored,
        @NotNull
        int goalsConceded,
        @NotNull
        int redCards,
        @NotNull
        int yellowCards,
        @NotBlank
        String captain
){
}
