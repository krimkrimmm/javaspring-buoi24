package vn.scrip.buoi24.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.MovieService;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class WebController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @GetMapping("/{id}")
    public String getMovieDetail(@PathVariable Integer id, Model model) {
        // Kiểm tra nếu không tìm thấy movie trong Optional
        Optional<Movie> movieOptional = movieService.getMovieById(id);

        if (!movieOptional.isPresent()) {
            // Nếu không tìm thấy phim, có thể redirect hoặc trả về trang lỗi
            return "error-page";  // hoặc trả về trang lỗi nào đó
        }

        Movie movie = movieOptional.get();  // Lấy đối tượng Movie ra từ Optional
        model.addAttribute("movie", movie);

        // Kiểm tra phim yêu thích
        boolean isFavorite = favoriteMovieService.isFavoriteMovie(id);
        model.addAttribute("isFavorite", isFavorite);

        return "movie-detail";  // Trả về view movie-detail
    }
}
