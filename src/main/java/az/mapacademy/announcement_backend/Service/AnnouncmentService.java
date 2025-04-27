package az.mapacademy.announcement_backend.Service;

import az.mapacademy.announcement_backend.Mapper.AnnoucmentMapper;
import az.mapacademy.announcement_backend.dao.AnnouncmentDao;
import az.mapacademy.announcement_backend.dao.jdbcimpl.AnnouncmentDaoJdbcImpl;
import az.mapacademy.announcement_backend.dto.AnnouncmentResponse;
import az.mapacademy.announcement_backend.dto.BaseResponse;
import az.mapacademy.announcement_backend.dto.CreateAnnouncmentRequest;
import az.mapacademy.announcement_backend.dto.UpdateAnnouncmentRequest;
import az.mapacademy.announcement_backend.entity.Announcment;
import az.mapacademy.announcement_backend.enums.SortDirection;
import az.mapacademy.announcement_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AnnouncmentService {

    private final AnnouncmentDao announcmentDao;
    private final UserService userService;

    public AnnouncmentService(@Qualifier("announcmentDaoJpaImpl") AnnouncmentDao announcmentDao, AnnoucmentMapper announcmentMapper, UserService userService) {
        this.announcmentDao = announcmentDao;
        this.announcmentMapper = announcmentMapper;
        this.userService = userService;
    }

    private final AnnoucmentMapper announcmentMapper;


    public BaseResponse<List<AnnouncmentResponse>> getAllAnnouncements(
            int page, int size, SortDirection sortCreatedDate, String name, String description) {
        Page<Announcment> announcementsPage = announcmentDao.findAll(page, size, sortCreatedDate, name, description);
        List<Announcment> announcements = announcementsPage.getContent();
        log.info("Announcements found: {}", announcements);


        Integer totalCount = announcmentDao.getAnnouncmentTotalCount();
        log.info("Total announcment count : {}", totalCount);
        var announcmentList = announcmentMapper.toResponseList(announcements);

        BaseResponse<List<AnnouncmentResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(announcmentList);
        baseResponse.setPageCount(announcementsPage.getTotalPages());
        return baseResponse;
    }

    public AnnouncmentResponse createAnnouncment(CreateAnnouncmentRequest request) {
        Announcment announcment = announcmentMapper.toEntity(request);
        log.info("Announcment create entity: {}", announcment);
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();//-  username goturmek
        var user = userService.getByUsername(username).orElseThrow
                (() -> new NotFoundException("User not found"));
        announcment.setUser(user);
         announcment = announcmentDao.create(announcment);
         return announcmentMapper.toResponseList(announcment);

    }

    public void updateAnnouncment(Long announcmentid, UpdateAnnouncmentRequest request) {
        Optional<Announcment> optAnnouncement = announcmentDao.findById(announcmentid);
        Announcment announcement = optAnnouncement.orElseThrow(() ->
                new NotFoundException("Announcement is not found with id: " + announcmentid));

        announcmentMapper.populate(request, announcement);
        log.info("Announcement update entity: {}", announcement);

        announcmentDao.update(announcement);
    }

}



