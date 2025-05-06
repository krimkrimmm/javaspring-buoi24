package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.User;

public interface UserService {
    User findByUsername(String username);
}
