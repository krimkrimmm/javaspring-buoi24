package vn.scrip.buoi24.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.service.MovieService;
import vn.scrip.buoi24.service.FavoriteMovieService;
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
        if (movieOpt.isPresent()) {
            Movie movie = movieOpt.get();
            model.addAttribute("movie", movie);

            // Check if user is logged in
            Optional<User> currentUserOpt = userService.getCurrentUser(); // This is Optional<User>

            if (currentUserOpt.isPresent()) {
                User currentUser = currentUserOpt.get();  // Get the actual User from the Optional
                boolean isFavorite = favoriteMovieService.isFavorite(currentUser, id);
                model.addAttribute("isFavorite", isFavorite);
            } else {
                model.addAttribute("isFavorite", false);
            }

            return "web/movie-detail";
        } else {
            return "error/404";
        }
    }
}
