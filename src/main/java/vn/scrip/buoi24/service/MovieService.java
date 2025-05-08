package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.Movie;
import java.util.Optional;

public interface MovieService {
    Optional<Movie> getMovieById(Integer id);

    Optional<Movie> getMovieByIdSlugAndIsActive(Integer id, String slug, boolean isActive);

    Optional<Movie> getMovieBySlugAndActive(String slug, boolean isActive);  // Thêm phương thức này
}
