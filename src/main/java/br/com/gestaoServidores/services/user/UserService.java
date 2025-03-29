package br.com.gestaoServidores.services.user;

import br.com.gestaoServidores.modules.User;
import br.com.gestaoServidores.record.user.UserAuthDTO;

public interface UserService {

    UserAuthDTO login(User user);

    UserAuthDTO register(User user);

    User getLoggedUser();
}
