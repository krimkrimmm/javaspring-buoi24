package vn.scrip.buoi24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private final FavoriteMovieRepository favoriteMovieRepository;
    private final MovieRepository movieRepository;

    @Override
    public void addFavoriteMovie(User user, Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            FavoriteMovie favorite = new FavoriteMovie();
            favorite.setUser(user);
            favorite.setMovie(movie.get());
            favoriteMovieRepository.save(favorite);
        }
    }

    @Override
    public void removeFavoriteMovie(User user, Integer movieId) {
        favoriteMovieRepository.deleteByUserIdAndMovieId(user.getId(), movieId);
    }

    @Override
    public boolean isFavoriteMovie(Integer movieId) {
        // ⚠️ Giả định bạn có logic lấy User từ SecurityContext
        User currentUser = getCurrentLoggedInUser(); // bạn cần hiện thực hàm này
        return favoriteMovieRepository.existsByUserIdAndMovieId(currentUser.getId(), movieId);
    }

    // TODO: Viết hàm lấy user đang đăng nhập
    private User getCurrentLoggedInUser() {
        // Ví dụ: dùng SecurityContextHolder để lấy user
        // return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        throw new UnsupportedOperationException("Chưa cài đặt logic lấy user hiện tại");
    }
}
