package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.entity.FavoriteMovie;

import java.util.List;
import java.util.Optional;

public interface FavoriteMovieService {
    Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId);
    void addFavoriteMovie(Integer userId, Integer movieId);
    void removeFavoriteMovie(Integer userId, Integer movieId);
    boolean isFavorite(User user, Movie movie);
    void toggleFavorite(User user, Movie movie);
    List<Movie> getFavoritesByUser(User user);
}
