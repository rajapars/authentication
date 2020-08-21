package xyz.tamanmain.authentication.user;

public interface UserService {

    User register(User newUser);

    User findByUsername(String username);

}
