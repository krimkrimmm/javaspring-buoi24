package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.User;

public interface FavoriteMovieService {

    void addFavoriteMovie(User user, Integer movieId);
    void removeFavoriteMovie(User user, Integer movieId);
    boolean isFavorite(User user, Integer movieId);  // Sử dụng Integer thay vì Movie
}
