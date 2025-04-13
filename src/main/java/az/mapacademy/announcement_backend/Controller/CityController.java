package az.mapacademy.announcement_backend.Controller;



import az.mapacademy.announcement_backend.Service.CityService;

import az.mapacademy.announcement_backend.dto.BaseResponse;
import az.mapacademy.announcement_backend.dto.CityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("api/v1/cities")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CityController {
    private final CityService cityService;
    @GetMapping
    public BaseResponse<List<CityDto>> getCities() {
        List<CityDto> cities = cityService.getAll();
        log.info("Get cities Api called");
        BaseResponse<List<CityDto>>baseResponse =new BaseResponse<>();
        baseResponse.setData(cities);

        return baseResponse; // Gelende bax bugun bitir!!!



    }
}





