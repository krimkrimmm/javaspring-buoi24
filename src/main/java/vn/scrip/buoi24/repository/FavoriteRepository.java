package vn.scrip.buoi24.repository;
import vn.scrip.buoi24.entity.Favorite;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndMovie(User user, Movie movie);
    boolean existsByUserAndMovie(User user, Movie movie);
    void deleteByUser(User user);
    void deleteByUserAndMovie(User user, Movie movie);
}
