package vn.scrip.buoi24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.FavoriteMovie;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.repository.FavoriteMovieRepository;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.FavoriteMovieService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    private final FavoriteMovieRepository favoriteMovieRepository;
    private final MovieRepository movieRepository;

    @Override
    public Optional<FavoriteMovie> findByUserIdAndMovieId(Integer userId, Integer movieId) {
        // Tìm kiếm phim yêu thích theo userId và movieId
        return favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId);
    }

    @Override
    public void addFavoriteMovie(Integer userId, Integer movieId) {
        // Kiểm tra nếu chưa có trong danh sách yêu thích thì thêm vào
        if (favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId).isEmpty()) {
            FavoriteMovie favoriteMovie = new FavoriteMovie();
            favoriteMovie.setUser(User.builder().id(userId).build()); // Tạo đối tượng User với ID
            favoriteMovie.setMovie(Movie.builder().id(movieId).build()); // Tạo đối tượng Movie với ID
            favoriteMovieRepository.save(favoriteMovie); // Lưu vào repository
        }
    }

    @Override
    public void removeFavoriteMovie(Integer userId, Integer movieId) {
        // Tìm kiếm phim yêu thích và xóa
        favoriteMovieRepository.findByUserIdAndMovieId(userId, movieId)
                .ifPresent(favoriteMovieRepository::delete); // Nếu tìm thấy thì xóa
    }

    @Override
    public boolean isFavorite(User user, Movie movie) {
        // Kiểm tra xem phim có phải là yêu thích của user không
        return favoriteMovieRepository.findByUserIdAndMovieId(user.getId(), movie.getId()).isPresent();
    }

    @Override
    public void toggleFavorite(User user, Movie movie) {
        // Chuyển đổi trạng thái yêu thích (thêm hoặc xóa)
        if (isFavorite(user, movie)) {
            removeFavoriteMovie(user.getId(), movie.getId());
        } else {
            addFavoriteMovie(user.getId(), movie.getId());
        }
    }

    @Override
    public List<Movie> getFavoritesByUser(User user) {
        // Lấy danh sách các phim yêu thích của user
        List<FavoriteMovie> favorites = favoriteMovieRepository.findAllByUserId(user.getId());
        return favorites.stream().map(FavoriteMovie::getMovie).collect(Collectors.toList()); // Chuyển đổi thành danh sách Movie
    }
}
