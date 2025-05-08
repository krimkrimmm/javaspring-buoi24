package vn.scrip.buoi24.controller.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Movie;

import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.MovieService;
import vn.scrip.buoi24.service.UserService;
import java.util.Optional;
@Controller
public class WebController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @Autowired
    private UserService userService;

    @GetMapping("/movie/{id}/{slug}")
    public String viewMovieDetail(@PathVariable Integer id,
                                  @PathVariable String slug,
                                  Model model) {
        Optional<Movie> movieOpt = movieService.getMovieByIdSlugAndIsActive(id, slug, true);
        if (movieOpt.isEmpty()) {
            return "error/404";
        }

        Movie movie = movieOpt.get();
        model.addAttribute("movie", movie);

        // Lấy user đang đăng nhập nếu có
        Optional<User> currentUserOpt = userService.getCurrentUser();
        boolean isFavorite = false;

        if (currentUserOpt.isPresent()) {
            User currentUser = currentUserOpt.get();
            isFavorite = favoriteMovieService.isFavorite(currentUser, id);
        }

        model.addAttribute("isFavorite", isFavorite);

        return "web/movie-detail";
    }

    @PostMapping("/movie/{id}/{slug}/favorite")
    public String toggleFavorite(@PathVariable Integer id,
                                 @PathVariable String slug) {
        Optional<Movie> movieOpt = movieService.getMovieByIdSlugAndIsActive(id, slug, true);
        Optional<User> currentUserOpt = userService.getCurrentUser();

        if (movieOpt.isPresent() && currentUserOpt.isPresent()) {
            Movie movie = movieOpt.get();
            User user = currentUserOpt.get();

            boolean isFavorite = favoriteMovieService.isFavorite(user, id);
            if (isFavorite) {
                favoriteMovieService.removeFavorite(user, movie);
            } else {
                favoriteMovieService.addFavorite(user, movie);
            }
        }

        return "redirect:/movie/" + id + "/" + slug;
    }
}
