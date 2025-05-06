package vn.scrip.buoi24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

@Service
@RequiredArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private final FavoriteMovieRepository favoriteMovieRepository;

    @Override
    public boolean isFavorite(User user, Movie movie) {
        return favoriteMovieRepository.existsByUserAndMovie(user, movie);
    }

    @Override
    public void addFavorite(User user, Movie movie) {
        if (!isFavorite(user, movie)) {
            FavoriteMovie favorite = new FavoriteMovie();
            favorite.setUser(user);
            favorite.setMovie(movie);
            favoriteMovieRepository.save(favorite);
        }
    }

    @Override
    public void removeFavorite(User user, Movie movie) {
        favoriteMovieRepository.deleteByUserAndMovie(user, movie);
    }
}
