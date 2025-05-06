package vn.scrip.buoi24.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.MovieService;

@Controller
@RequestMapping("/movie")
public class WebController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @GetMapping("/detail/{movieId}/{slug}")
    public String movieDetail(@PathVariable Integer movieId, @PathVariable String slug, Model model) {
        // Lấy thông tin chi tiết phim
        Movie movie = movieService.findMovieDetails(movieId, slug);
        model.addAttribute("movie", movie);

        // Kiểm tra nếu người dùng đang đăng nhập và có phim yêu thích
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isFavorite = favoriteMovieService.findByUserIdAndMovieId(user.getId(), movieId).isPresent();
        model.addAttribute("isFavorite", isFavorite);

        return "movieDetail"; // Trang chi tiết phim
    }

    @PostMapping("/favorite/{movieId}")
    public String addFavoriteMovie(@PathVariable Integer movieId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favoriteMovieService.addFavoriteMovie(user.getId(), movieId);
        return "redirect:/movie/detail/" + movieId;
    }

    @PostMapping("/unfavorite/{movieId}")
    public String removeFavoriteMovie(@PathVariable Integer movieId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        favoriteMovieService.removeFavoriteMovie(user.getId(), movieId);
        return "redirect:/movie/detail/" + movieId;
    }
}
