package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.FavoriteMovie;

import java.util.List;
import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);
    List<FavoriteMovie> findAllByUserId(Integer userId);
}
