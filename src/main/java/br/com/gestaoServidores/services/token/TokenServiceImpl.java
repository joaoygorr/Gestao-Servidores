package br.com.gestaoServidores.services.token;

import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.User;
import br.com.gestaoServidores.record.user.UserRefreshDTO;
import br.com.gestaoServidores.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final UserRepository userRepository;

    @Override
    public String generateToken(User user) {
        try {
            return JWT.create().withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(Algorithm.HMAC256(secret));

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error na autenticação");
        }
    }

    @Override
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    @Override
    public String generateRefreshToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(this.generateExpirationDate())
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public UserRefreshDTO refreshToken(String token) {
        String email = validateToken(token);

        if (email == null) throw new RuntimeException("Refresh token inválido ou expirado");

        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception404("Usuário não encontrado"));

        return new UserRefreshDTO(generateRefreshToken(user.getEmail()), token);
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusMinutes(8000).atZone(ZoneId.systemDefault()).toInstant();
    }
}
