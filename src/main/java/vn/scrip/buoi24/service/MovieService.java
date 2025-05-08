package vn.scrip.buoi24.service;

import vn.scrip.buoi24.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Integer id);
}
