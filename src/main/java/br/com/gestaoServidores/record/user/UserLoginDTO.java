package br.com.gestaoServidores.record.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(@NotBlank(message = "E-mail não pode estar em branco") String email,
                           @NotBlank(message = "Senha não pode estar em branco") String password) {
}
