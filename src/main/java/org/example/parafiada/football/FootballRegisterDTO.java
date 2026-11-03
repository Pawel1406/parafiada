package org.example.parafiada.football;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FootballRegisterDTO(
        @NotNull
        Category footballCategory,
        @NotBlank
        String name,
        @NotBlank
        String captain
) {
}
