package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;

public interface FavoriteMovieService {

    void addFavorite(User user, Movie movie);

    void removeFavorite(User user, Movie movie);

    boolean isFavorite(User user, Integer movieId);

    void addFavoriteMovie(User user, Integer movieId);

    void removeFavoriteMovie(User user, Integer movieId);
}
