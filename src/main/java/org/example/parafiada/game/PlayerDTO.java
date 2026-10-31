package org.example.parafiada.game;

import jakarta.validation.constraints.NotBlank;

public record PlayerDTO(
        @NotBlank String firstName,
        @NotBlank String lastName) {
}
