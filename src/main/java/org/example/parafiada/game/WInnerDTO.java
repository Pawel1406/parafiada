package org.example.parafiada.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WInnerDTO (
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotNull
        int points
){
}
