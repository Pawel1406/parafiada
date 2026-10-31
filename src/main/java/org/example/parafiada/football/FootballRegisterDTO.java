package org.example.parafiada.football;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FootballRegisterDTO(
        @NotNull
        FootballCategory footballCategory,
        @NotBlank
        String name,
        @NotBlank
        String captain
) {
}
