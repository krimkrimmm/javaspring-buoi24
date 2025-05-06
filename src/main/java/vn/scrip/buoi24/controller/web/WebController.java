package vn.scrip.buoi24.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.scrip.buoi24.entity.Episode;
import vn.scrip.buoi24.entity.Movie;
import vn.scrip.buoi24.entity.User;
import vn.scrip.buoi24.model.enums.MovieType;
import vn.scrip.buoi24.service.EpisodeService;
import vn.scrip.buoi24.service.FavoriteMovieService;
import vn.scrip.buoi24.service.MovieService;
import vn.scrip.buoi24.service.UserService;

import java.util.List;
import org.springframework.data.domain.Page;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final MovieService movieService;
    private final EpisodeService episodeService;
    private final UserService userService;
    private final FavoriteMovieService favoriteMovieService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Movie> hotMovies = movieService.findHotMovies(true, 4);
        List<Movie> phimLeList = movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent();
        List<Movie> phimBoList = movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent();
        List<Movie> phimChieuRapList = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent();

        model.addAttribute("hotMovies", hotMovies);
        model.addAttribute("phimLeList", phimLeList);
        model.addAttribute("phimBoList", phimBoList);
        model.addAttribute("phimChieuRapList", phimChieuRapList);

        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,
                                      @PathVariable String slug,
                                      Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        if (movie == null) {
            return "redirect:/";
        }

        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        model.addAttribute("movie", movie);
        model.addAttribute("episodes", episodes);
        model.addAttribute("isFavorite", isFavoriteMovie(movie));

        return "web/chi-tiet-phim";
    }

    @PostMapping("/phim/{id}/{slug}/favorite")
    public String toggleFavorite(@PathVariable Integer id,
                                 @PathVariable String slug) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null || username.equals("anonymousUser")) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(username);
        Movie movie = movieService.findMovieDetails(id, slug);
        if (user == null || movie == null) {
            return "redirect:/";
        }

        if (favoriteMovieService.isFavorite(user, movie)) {
            favoriteMovieService.removeFavorite(user, movie);
        } else {
            favoriteMovieService.addFavorite(user, movie);
        }

        return "redirect:/phim/" + id + "/" + slug;
    }

    private boolean isFavoriteMovie(Movie movie) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username == null || username.equals("anonymousUser")) {
            return false;
        }

        User user = userService.findByUsername(username);
        return user != null && favoriteMovieService.isFavorite(user, movie);
    }
}
