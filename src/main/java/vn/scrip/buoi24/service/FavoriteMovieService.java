package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.User;

public interface FavoriteMovieService {

    void addFavoriteMovie(User user, Integer movieId);

    void removeFavoriteMovie(User user, Integer movieId);

    boolean isFavoriteMovie(Integer movieId);
}
