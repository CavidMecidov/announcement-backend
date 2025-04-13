package az.mapacademy.announcement_backend.Repository;

import az.mapacademy.announcement_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
