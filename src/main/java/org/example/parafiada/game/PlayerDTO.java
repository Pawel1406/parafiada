package org.example.parafiada.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.parafiada.football.Category;


public record PlayerDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull Category category)  {
}
