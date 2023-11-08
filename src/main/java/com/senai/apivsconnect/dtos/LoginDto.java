package com.senai.apivsconnect.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank @Email(message = "O email deve estar em um formato válido") String email,
        @NotBlank String senha
) {
}
