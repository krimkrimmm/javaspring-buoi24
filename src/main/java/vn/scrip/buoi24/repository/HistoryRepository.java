package vn.scrip.buoi24.repository;
import vn.scrip.buoi24.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}