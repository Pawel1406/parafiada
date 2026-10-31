package org.example.parafiada.football;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FootbalTeamDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        FootballCategory footballCategory,
        @NotBlank
        String captain
) {
}
