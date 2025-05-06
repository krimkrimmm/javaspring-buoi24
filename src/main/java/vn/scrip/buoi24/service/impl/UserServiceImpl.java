package vn.scrip.buoi24.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.UserRepository;
import vn.scrip.buoi24.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends UserService {
    private final UserRepository userRepository;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}



