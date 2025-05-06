package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
