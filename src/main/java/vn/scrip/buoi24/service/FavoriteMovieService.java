package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;

import java.util.List;
import java.util.Optional;

public interface FavoriteMovieService {

    // Tìm kiếm phim yêu thích của user theo userId và movieId
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);

    // Thêm phim yêu thích cho user
    void addFavoriteMovie(Integer userId, Integer movieId);

    // Xóa phim yêu thích của user
    void removeFavoriteMovie(Integer userId, Integer movieId);

    // Kiểm tra phim có phải là yêu thích của user không
    boolean isFavorite(User user, Movie movie);

    // Chuyển đổi trạng thái yêu thích (thêm/xóa)
    void toggleFavorite(User user, Movie movie);

    // Lấy danh sách phim yêu thích của user
    List<Movie> getFavoritesByUser(User user);
}
