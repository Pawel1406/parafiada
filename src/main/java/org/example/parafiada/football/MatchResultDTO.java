package org.example.parafiada.football;

import jakarta.validation.constraints.NotNull;

public record MatchResultDTO(
        @NotNull
        Long firstID,
        @NotNull
        Long secondID,

        @NotNull
        int firstGoalsScored,
        @NotNull
        int firstGoalsConceded,

        @NotNull
        int firstYellowCards,
        @NotNull
        int firstRedCards,

        @NotNull
        int secondGoalsScored,
        @NotNull
        int secondGoalsConceded,

        @NotNull
        int secondYellowCards,
        @NotNull
        int secondRedCards,

        Long idWinnerPenaltyKicks
) {}
