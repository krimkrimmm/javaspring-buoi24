package vn.scrip.buoi24.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.MovieService;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> getMovieByIdSlugAndIsActive(Integer id, String slug, boolean isActive) {
        return movieRepository.findByIdAndSlugAndIsActive(id, slug, isActive);
    }

    @Override
    public Optional<Movie> getMovieBySlugAndActive(String slug, boolean isActive) {
        return movieRepository.findBySlugAndIsActive(slug, isActive);  // Cập nhật phương thức này
    }
}
