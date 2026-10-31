package org.example.parafiada.football;

import jakarta.validation.constraints.NotNull;

public record MatchDTO(
        @NotNull
        Long winnerID,
        @NotNull
        Long loserID,

        @NotNull
        int winnerGoalsScored,
        @NotNull
        int winnerGoalsConceded,

        @NotNull
        int winnerYellowCards,
        @NotNull
        int winnerRedCards,

        @NotNull
        int loserGoalsScored,
        @NotNull
        int loserGoalsConceded,

        @NotNull
        int loserYellowCards,
        @NotNull
        int loserRedCards
) {
}
