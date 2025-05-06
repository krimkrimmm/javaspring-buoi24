package vn.scrip.buoi24.controller.web;
import vn.scrip.buoi24.entity.Favorite;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.service.FavoriteService;
import vn.scrip.buoi24.service.UserService;

import vn.scrip.buoi24.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public String getMovieDetail(@PathVariable Integer id, Model model, Principal principal) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with id " + id));  // Kiểm tra phim tồn tại
        model.addAttribute("movie", movie);

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);  // Lấy người dùng từ tên đăng nhập

            boolean isFavorite = favoriteService.isFavorite(user, id);  // Kiểm tra xem phim có yêu thích không
            model.addAttribute("isFavorite", isFavorite);
        }
        return "movie-detail";  // Trả về trang chi tiết phim
    }

    @PostMapping("/{id}/favorite")
    public String toggleFavorite(@PathVariable Integer id, Principal principal) {
        if (principal != null)
        {
            Movie movie = movieRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Movie not found with id " + id));  // Kiểm tra phim tồn tại
            User user = userService.findByUsername(principal.getName());  // Lấy người dùng đăng nhập
            if (favoriteService.isFavorite(user, id)) {
                favoriteService.removeFavorite(user, id);  // Xóa phim khỏi yêu thích
            } else {
                favoriteService.addFavorite(user, id);  // Thêm phim vào yêu thích
            }
        }

        return "redirect:/movies/" + id;  // Quay lại trang chi tiết phim
    }
}







