package az.mapacademy.announcement_backend.dao;

import az.mapacademy.announcement_backend.entity.Announcment;
import az.mapacademy.announcement_backend.enums.SortDirection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AnnouncmentDao {
    Page<Announcment> findAll(int page, int size, SortDirection sortCreatedDat,String name,String description);
    Announcment create(Announcment announcment);
    void update(Announcment announcment);
    Optional<Announcment> findById(Long announcementId);
    Integer getAnnouncmentTotalCount();
}
