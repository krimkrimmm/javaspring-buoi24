package vn.scrip.buoi24.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.MovieService;
import vn.scrip.buoi24.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class WebController {

    private final MovieService movieService;
    private final UserService userService;
    private final FavoriteMovieService favoriteMovieService;

    // Trang chủ - hiển thị danh sách phim
    @GetMapping
    public String home(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "web/index";
    }

    // Trang chi tiết phim
    @GetMapping("/movies/{id}")
    public String getMovieDetail(@PathVariable Integer id, Model model, Principal principal) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);

        boolean isFavorite = false;

        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            isFavorite = favoriteMovieService.isFavorite(user, movie);
        }

        model.addAttribute("isFavorite", isFavorite);
        return "web/movie-detail";
    }

    // Toggle phim yêu thích (POST)
    @PostMapping("/movies/{id}/favorite")
    public String toggleFavoriteMovie(@PathVariable Integer id, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            Movie movie = movieService.findById(id);
            favoriteMovieService.toggleFavorite(user, movie);
        }
        return "redirect:/movies/" + id;
    }
}
