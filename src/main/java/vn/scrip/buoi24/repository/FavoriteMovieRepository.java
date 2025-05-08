package vn.scrip.buoi24.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.entity.Movie;

import java.util.Optional;
import java.util.List;


@Repository
public interface FavoriteMovieRepository extends JpaRepository<FavoriteMovie, Integer> {

    FavoriteMovie findByUserAndMovieId(User user, Integer movieId);
    // Tìm phim yêu thích theo user và movie
    Optional<FavoriteMovie> findByUserAndMovie(User user, Movie movie);

    // Lấy danh sách phim yêu thích của 1 user
    List<FavoriteMovie> findByUser(User user);

    // Xoá phim yêu thích theo user và movie
    void deleteByUserAndMovie(User user, Movie movie);

    // Kiểm tra user đã yêu thích movie hay chưa
    boolean existsByUserAndMovie(User user, Movie movie);
}

