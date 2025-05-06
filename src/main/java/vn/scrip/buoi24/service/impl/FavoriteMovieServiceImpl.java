package vn.scrip.buoi24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

import java.util.Optional;

@Service
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    @Autowired
    private FavoriteMovieRepository favoriteMovieRepository;

    @Override
    public Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId) {
        return favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public void addFavoriteMovie(Integer userId, Integer movieId) {
        // Logic thêm phim yêu thích
    }

    @Override
    public void removeFavoriteMovie(Integer userId, Integer movieId) {
        // Logic xóa phim yêu thích
    }
}
