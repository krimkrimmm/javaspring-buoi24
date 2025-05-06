package vn.scrip.buoi24.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.model.enums.MovieType;
import vn.scrip.buoi24.repository.MovieRepository;
import vn.scrip.buoi24.service.MovieService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findHotMovies(boolean isHot, int limit) {
        return movieRepository.findTopByIsHotAndIsActiveOrderByIdDesc(isHot, true, PageRequest.of(0, limit)).getContent();
    }

    @Override
    public Page<Movie> findByType(MovieType type, boolean isActive, int page, int pageSize) {
        return movieRepository.findByTypeAndIsActive(type, isActive, PageRequest.of(page, pageSize));
    }

    @Override
    public Movie findMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndIsActive(id, slug, true).orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
