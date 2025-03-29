package br.com.gestaoServidores.services.token;

import br.com.gestaoServidores.modules.User;

public interface TokenService {

    String generateToken(User user);

    String validateToken(String token);
}
