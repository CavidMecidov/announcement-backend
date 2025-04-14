package az.mapacademy.announcement_backend.Repository;


import az.mapacademy.announcement_backend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}