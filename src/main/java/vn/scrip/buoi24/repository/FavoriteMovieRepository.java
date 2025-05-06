package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {
    boolean existsByUserAndMovie(User user, Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
}
