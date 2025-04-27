package az.mapacademy.announcement_backend.Controller;

import az.mapacademy.announcement_backend.Service.AnnouncmentService;
import az.mapacademy.announcement_backend.Service.JwtService;
import az.mapacademy.announcement_backend.dto.*;
import az.mapacademy.announcement_backend.enums.SortDirection;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/annoucnements")
public class AnnouncmentController {
    private final AnnouncmentService announcmentService;


    @GetMapping
    public BaseResponse<List<AnnouncmentResponse>> getAnnouncments(@RequestParam("page") int page,
                                                                   @RequestParam("size") int size,
                                                                   @RequestParam(value = "sortByCreatedDate", required = false) SortDirection sortCreatedDate,
                                                                   @RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                   @RequestParam(value = "description", required = false, defaultValue = "") String description)
                                                                   {

        log.info("Get announcements API is called");

        return announcmentService.getAllAnnouncements(page, size, sortCreatedDate, name, description);
    }

    @PostMapping
    public BaseResponse<AnnouncmentResponse> create(@RequestBody @Valid CreateAnnouncmentRequest request) {
        log.info("Create  annoucment API called,request: {}", request);
        var response = announcmentService.createAnnouncment(request);
        BaseResponse<AnnouncmentResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(response);
        return baseResponse;
    }

    @PutMapping("{announcmentid}")
    public void update(@RequestBody @PathVariable("announcmentid") @Valid Long annoucmentid, UpdateAnnouncmentRequest request) {
        log.info("Update announcment API called , announcment_id: {},request: {}", annoucmentid, request);
        announcmentService.updateAnnouncment(annoucmentid, request);
    }


}
