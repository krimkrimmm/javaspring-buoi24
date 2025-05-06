package vn.scrip.buoi24.repository;

import vn.scrip.buoi24.entity.FavoriteMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {

    // Tìm kiếm FavoriteMovie theo userId và movieId
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);

    // Tìm tất cả FavoriteMovie của user theo userId
    List<FavoriteMovie> findAllByUserId(Integer userId);
}
