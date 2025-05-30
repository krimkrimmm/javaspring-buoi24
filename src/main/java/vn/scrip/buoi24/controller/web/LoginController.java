package vn.scrip.buoi24.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // Trả về giao diện đăng nhập
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "auth/logout"; //
    }
}