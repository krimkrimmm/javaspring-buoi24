package vn.scrip.buoi24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private final FavoriteMovieRepository favoriteMovieRepository;

    @Override
    public boolean isFavorite(User user, Movie movie) {
        return favoriteMovieRepository.findByUserAndMovie(user, movie).isPresent();
    }

    @Override
    public void toggleFavorite(User user, Movie movie) {
        Optional<FavoriteMovie> favorite = favoriteMovieRepository.findByUserAndMovie(user, movie);
        if (favorite.isPresent()) {
            favoriteMovieRepository.delete(favorite.get());
        } else {
            FavoriteMovie newFavorite = FavoriteMovie.builder()
                    .user(user)
                    .movie(movie)
                    .build();
            favoriteMovieRepository.save(newFavorite);
        }
    }
}
