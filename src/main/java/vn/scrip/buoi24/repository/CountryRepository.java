package vn.scrip.buoi24.repository;
import vn.scrip.buoi24.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CountryRepository extends JpaRepository<Country, Integer> {
}