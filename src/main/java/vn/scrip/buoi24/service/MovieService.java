package vn.scrip.buoi24.service;

import org.springframework.data.domain.Page;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.model.enums.MovieType;

import java.util.List;

public interface MovieService {
    List<Movie> findHotMovies(boolean isHot, int limit);
    Page<Movie> findByType(MovieType type, boolean isActive, int page, int pageSize);
    Movie findMovieDetails(Integer id, String slug); // Phương thức lấy chi tiết phim
}
