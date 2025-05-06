package vn.scrip.buoi24.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.scrip.buoi24.entity.FavoriteMovie;

import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {

    // Tìm FavoriteMovie theo userId và movieId
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);

    // Xóa FavoriteMovie theo userId và movieId
    void deleteByUserIdAndMovieId(Integer userId, Integer movieId);
}




