package vn.scrip.buoi24.controller.web;

import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{slug}")
    public Optional<Movie> getMovieBySlug(@PathVariable String slug) {
        // Trả về phim theo slug và trạng thái active
        return movieService.getMovieBySlugAndActive(slug, true);
    }
}
