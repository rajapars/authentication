package xyz.tamanmain.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.tamanmain.authentication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
