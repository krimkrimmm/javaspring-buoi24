package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.FavoriteMovie;

import java.util.Optional;

public interface FavoriteMovieService {

    // Tìm FavoriteMovie theo userId và movieId
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);

    // Thêm, xóa phim yêu thích
    void addFavoriteMovie(Integer userId, Integer movieId);
    void removeFavoriteMovie(Integer userId, Integer movieId);
}
