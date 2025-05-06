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

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;
    private final MovieService movieService;
    private final FavoriteMovieService favoriteMovieService;

    @PostMapping("/movie/{id}/favorite")
    public String toggleFavorite(@PathVariable Integer id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Movie movie = movieService.findById(id);
        favoriteMovieService.toggleFavorite(user, movie);
        return "redirect:/movie/" + id;
    }

    @GetMapping("/user/favorites")
    public String showFavorites(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("favorites", favoriteMovieService.getFavoritesByUser(user));
        return "user/favorites";
    }

    @GetMapping("/movie/{id}")
    public String movieDetail(@PathVariable Integer id, Model model, Principal principal) {
        Movie movie = movieService.findById(id);
        model.addAttribute("movie", movie);
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            boolean isFavorite = favoriteMovieService.isFavorite(user, movie);
            model.addAttribute("isFavorite", isFavorite);
        }
        return "movie/detail";
    }
}
