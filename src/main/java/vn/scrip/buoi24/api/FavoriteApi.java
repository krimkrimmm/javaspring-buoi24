package vn.scrip.buoi24.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.UserService;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteApi {

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/{movieId}")
    public String addFavoriteMovie(@PathVariable Integer movieId) {
        userService.getCurrentUser().ifPresent(user -> {
            favoriteMovieService.addFavoriteMovie(user, movieId);
        });
        return "Movie added to favorites!";
    }

    @DeleteMapping("/remove/{movieId}")
    public String removeFavoriteMovie(@PathVariable Integer movieId) {
        userService.getCurrentUser().ifPresent(user -> {
            favoriteMovieService.removeFavoriteMovie(user, movieId);
        });
        return "Movie removed from favorites!";
    }
}
