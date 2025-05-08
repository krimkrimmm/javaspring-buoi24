package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;

import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {
    void deleteByUserIdAndMovieId(Integer userId, Integer movieId);
    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);
}
