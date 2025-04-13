package az.mapacademy.announcement_backend.dao.jpaimlp;

import az.mapacademy.announcement_backend.Repository.AnnouncementRepository;
import az.mapacademy.announcement_backend.dao.AnnouncmentDao;
import az.mapacademy.announcement_backend.entity.Announcment;
import az.mapacademy.announcement_backend.enums.SortDirection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Repository("announcmentDaoJpaImpl")
public class AnnouncmentDaoJpaImpl implements AnnouncmentDao {
    private final AnnouncementRepository announcementRepository;

    @Override
    public Page<Announcment> findAll(int page, int size, SortDirection sortCreatedDate,String name,String description) {
       log.info((" Find all Announcements method is called from Jpa implementation of AnnouncementDao"));
      Sort sort = null;
      if(sortCreatedDate==SortDirection.ASC){
          sort = Sort.by(Sort.Direction.ASC,"Created_date");
      }
      else if(sortCreatedDate ==SortDirection.DESC){
          sort = Sort.by(Sort.Direction.DESC,"Created_date");
      }
        Pageable pageable =null;
      if(sort!=null){
          PageRequest.of(page-1,size,sort);
      }
      else{
          PageRequest.of(page-1,size,sort);
      }

        return (Page<Announcment>) announcementRepository.findAllByNameContainingAndDescription(name,description,pageable);

    }

    @Override
    public void create(Announcment announcment) {
        System.out.println("Create in Jpa is call");
        announcementRepository.save(announcment);

    }

    @Override
    public void update(Announcment announcment) {
        announcementRepository.save(announcment);


    }

    @Override
    public Optional<Announcment> findById(Long announcementId) {
        log.info("Find announcement is called from jpa impl of AnnouncementDao");

        return announcementRepository.findById(announcementId);
    }

    @Override
    public Integer getAnnouncmentTotalCount() {
        System.out.println("Getting total count of announcement is called from   jpa impl of AnnouncmenetsDao");
        return (int) announcementRepository.count();
    }
}
