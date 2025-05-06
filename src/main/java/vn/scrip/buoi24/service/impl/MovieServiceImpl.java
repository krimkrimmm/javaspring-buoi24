package vn.scrip.buoi24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.exception.NotFoundException;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.MovieService;
import vn.scrip.buoi24.model.enums.MovieType;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findHotMovies(boolean isHot, int limit) {
        // TODO: Triển khai sau nếu cần
        return null;
    }

    @Override
    public Page<Movie> findByType(MovieType type, boolean isActive, int page, int pageSize) {
        // TODO: Triển khai sau nếu cần
        return null;
    }

    @Override
    public Movie findMovieDetails(Integer id, String slug) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            Movie m = movie.get();
            if (m.getSlug().equals(slug)) {
                return m;
            } else {
                throw new RuntimeException("Movie slug does not match!");
            }
        } else {
            throw new RuntimeException("Movie not found with id " + id);
        }
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim với ID: " + id));
    }
}
