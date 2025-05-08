package vn.scrip.buoi24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.repository.UserRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

@Service
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addFavoriteMovie(User user, Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            favoriteMovie.setUser(user);
            favoriteMovie.setMovie(movie);
            favoriteMovieRepository.save(favoriteMovie);
        }
    }

    @Override
    public void removeFavoriteMovie(User user, Integer movieId) {
        FavoriteMovie favoriteMovie = favoriteMovieRepository.findByUserAndMovieId(user, movieId);
        if (favoriteMovie != null) {
            favoriteMovieRepository.delete(favoriteMovie);
        }
    }

    @Override
    public boolean isFavorite(User user, Integer movieId) {  // Sử dụng Integer movieId
        FavoriteMovie favoriteMovie = favoriteMovieRepository.findByUserAndMovieId(user, movieId);
        return favoriteMovie != null;
    }
}
