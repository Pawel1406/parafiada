package org.example.parafiada.register;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import org.example.parafiada.login.Role;

public record RegisterDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotNull
        Role role
) {
}
