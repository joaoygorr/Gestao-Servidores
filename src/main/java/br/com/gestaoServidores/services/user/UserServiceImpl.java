package br.com.gestaoServidores.services.user;

import br.com.gestaoServidores.core.exceptions.Exception401;
import br.com.gestaoServidores.core.exceptions.Exception404;
import br.com.gestaoServidores.modules.User;
import br.com.gestaoServidores.record.user.UserAuthDTO;
import br.com.gestaoServidores.repositories.UserRepository;
import br.com.gestaoServidores.services.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAuthDTO login(User entity) {
        User user = this.userRepository.findByEmail(entity.getEmail())
                .orElseThrow(() -> new Exception404("Usuário não encontrado"));

        if (passwordEncoder.matches(entity.getPassword(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return new UserAuthDTO(user.getName(), token);
        }
        throw new Exception401("Credenciais inválidas");
    }

    @Override
    public UserAuthDTO register(User entity) {
        Optional<User> user = this.userRepository.findByEmail(entity.getEmail());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(entity.getPassword()));
            newUser.setEmail(entity.getEmail());
            newUser.setName(entity.getName());
            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return new UserAuthDTO(newUser.getName(), token);
        }

        throw new Exception401("Credenciais inválidas");
    }
}
