package br.com.gestaoServidores.controllers;

import br.com.gestaoServidores.core.mappers.UserMapper;
import br.com.gestaoServidores.record.user.UserAuthDTO;
import br.com.gestaoServidores.record.user.UserDTO;
import br.com.gestaoServidores.record.user.UserLoginDTO;
import br.com.gestaoServidores.record.user.UserRefreshDTO;
import br.com.gestaoServidores.services.token.TokenService;
import br.com.gestaoServidores.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoint relacionado a autenticação")
public class AuthController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário",
            description = "Realiza a autenticação de um usuário com base nas credenciais fornecidas e retorna um token de autenticação.")
    public ResponseEntity<UserAuthDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService.login(userMapper.toEntity(userLoginDTO)));
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar um novo usuário",
            description = "Cria um novo usuário no sistema com base nos dados fornecidos na requisição. Retorna os detalhes do usuário registrado."
    )
    public ResponseEntity<UserAuthDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userService.register(userMapper.toEntity(userDTO)));
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Renovar o token de autenticação", description = "Renova o token JWT utilizando um refresh token válido.")
    public ResponseEntity<UserRefreshDTO> refreshToken(@RequestBody String refreshToken) {
        return ResponseEntity.status(HttpStatus.OK).body(this.tokenService.refreshToken(refreshToken));
    }
}
