package vn.scrip.buoi24.service;
import vn.scrip.buoi24.entity.User;
import java.util.Optional;
public interface UserService {
    Optional<User> getCurrentUser();
}