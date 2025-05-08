package vn.scrip.buoi24.controller.web;

import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id}/{slug}")
    public String viewMovieDetail(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Optional<Movie> movieOpt = movieService.getMovieByIdSlugAndIsActive(id, slug, true);
        if (movieOpt.isPresent()) {
            model.addAttribute("movie", movieOpt.get());
            return "web/movie-detail";
        } else {
            return "error/404"; // Hoặc redirect về trang lỗi
        }
    }
}
