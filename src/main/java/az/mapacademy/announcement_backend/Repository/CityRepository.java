package az.mapacademy.announcement_backend.Repository;

import az.mapacademy.announcement_backend.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
