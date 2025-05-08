package vn.scrip.buoi24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

import java.util.Optional;

@Service
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void addFavoriteMovie(User user, Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        if (!favoriteMovieRepository.existsByUserAndMovie(user, movie)) {
            FavoriteMovie favoriteMovie = new FavoriteMovie(user, movie);
            favoriteMovieRepository.save(favoriteMovie);
        }
    }

    @Override
    public void removeFavoriteMovie(User user, Integer movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        favoriteMovieRepository.deleteByUserAndMovie(user, movie);
    }

    @Override
    public boolean isFavorite(User user, Integer movieId) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        return movieOpt.map(movie -> favoriteMovieRepository.existsByUserAndMovie(user, movie))
                .orElse(false);
    }

    @Override
    public void addFavorite(User user, Movie movie) {
        if (!favoriteMovieRepository.existsByUserAndMovie(user, movie)) {
            FavoriteMovie favoriteMovie = new FavoriteMovie(user, movie);
            favoriteMovieRepository.save(favoriteMovie);
        }
    }

    @Override
    public void removeFavorite(User user, Movie movie) {
        favoriteMovieRepository.deleteByUserAndMovie(user, movie);
    }
}
