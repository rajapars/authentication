package xyz.tamanmain.authentication.service;

import xyz.tamanmain.authentication.model.User;

public interface UserService {

    User register(User newUser);

    User findByUsername(String username);

}
