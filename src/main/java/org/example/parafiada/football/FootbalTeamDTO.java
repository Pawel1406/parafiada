package org.example.parafiada.football;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FootbalTeamDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotNull
        Category footballCategory,
        @NotBlank
        String captain
) {
}
