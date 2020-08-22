package xyz.tamanmain.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.tamanmain.authentication.model.User;
import xyz.tamanmain.authentication.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public User register(User user) {
        Optional<User> currentUser = userRepository.findById(user.getUsername());
        if (currentUser.isPresent()) {
            return null;
        }

        user.setPassword(bcryptEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

}
