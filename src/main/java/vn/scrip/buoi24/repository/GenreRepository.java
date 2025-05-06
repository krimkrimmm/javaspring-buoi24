package vn.scrip.buoi24.repository;
import vn.scrip.buoi24.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}