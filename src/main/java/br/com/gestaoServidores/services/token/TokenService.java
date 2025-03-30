package br.com.gestaoServidores.services.token;

import br.com.gestaoServidores.modules.User;
import br.com.gestaoServidores.record.user.UserRefreshDTO;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);

    String generateRefreshToken(String token);

    UserRefreshDTO refreshToken(String token);
}
