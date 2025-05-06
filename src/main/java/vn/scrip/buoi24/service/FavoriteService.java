package vn.scrip.buoi24.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.Favorite;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteRepository;
import vn.scrip.buoi24.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;

    public List<Favorite> getFavorites(User user) {
        return favoriteRepository.findByUser(user);
    }

    public void addFavorite(User user, Integer movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) return;

        Movie movie = movieOpt.get();

        if (!favoriteRepository.existsByUserAndMovie(user, movie)) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setMovie(movie);
            favoriteRepository.save(favorite);
        }
    }

    public void removeFavorite(User user, Integer movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) return;

        Movie movie = movieOpt.get();
        favoriteRepository.deleteByUserAndMovie(user, movie);
    }

    public void removeAllFavorites(User user) {
        favoriteRepository.deleteByUser(user);
    }

    public boolean isFavorite(User user, Integer movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) return false;

        Movie movie = movieOpt.get();
        return favoriteRepository.existsByUserAndMovie(user, movie);
    }
}
