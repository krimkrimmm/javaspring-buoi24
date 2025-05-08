package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.Movie;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByIdAndSlugAndIsActive(Integer id, String slug, boolean isActive);

    Optional<Movie> findBySlugAndIsActive(String slug, boolean isActive);  // Thêm phương thức này
}
