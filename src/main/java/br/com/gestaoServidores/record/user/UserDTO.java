package br.com.gestaoServidores.record.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank(message = "Nome não pode estar em branco") String name,
                      @NotBlank(message = "E-mail não pode estar em branco") String email,
                      @NotBlank(message = "Senha não pode estar em branco") String password) {
}
