package vn.scrip.buoi24.repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.model.enums.MovieType;

import java.util.List;
import java.util.Optional;
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Page<Movie> findTopByIsHotAndIsActiveOrderByIdDesc(boolean isHot, boolean isActive, Pageable pageable);
    Page<Movie> findByTypeAndIsActive(MovieType type, boolean isActive, Pageable pageable);
    Optional<Movie> findByIdAndSlugAndIsActive(Integer id, String slug, boolean isActive);

}


