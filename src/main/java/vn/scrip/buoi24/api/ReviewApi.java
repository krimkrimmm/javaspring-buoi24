package vn.scrip.buoi24.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.service.MovieService;

import java.util.Optional;

@RestController
public class ReviewApi {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable Integer id) {
        // Kiểm tra xem Movie có tồn tại trong Optional hay không
        Optional<Movie> movieOptional = movieService.getMovieById(id);

        // Nếu không có phim, throw lỗi NotFoundException hoặc trả về thông báo lỗi khác
        return movieOptional.orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }
}
