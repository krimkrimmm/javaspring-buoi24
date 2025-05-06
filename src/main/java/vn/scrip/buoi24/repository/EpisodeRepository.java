package vn.scrip.buoi24.repository;
import vn.scrip.buoi24.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer id, Boolean status);

    Episode findByMovie_IdAndDisplayOrderAndStatus(Integer id, Integer displayOrder, Boolean status);
}