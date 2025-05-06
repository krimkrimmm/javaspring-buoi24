package vn.scrip.buoi24.service;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;

public interface FavoriteMovieService {
    boolean isFavorite(User user, Movie movie);
    void addFavorite(User user, Movie movie);
    void removeFavorite(User user, Movie movie);
}
