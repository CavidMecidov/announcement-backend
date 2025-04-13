package az.mapacademy.announcement_backend.Controller;

import az.mapacademy.announcement_backend.Service.CategoryService;
import az.mapacademy.announcement_backend.dto.BaseResponse;
import az.mapacademy.announcement_backend.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService ;

    @GetMapping
    public BaseResponse<List<CategoryDto>>getAllCategories(){
        log.info("Get categories Api called");
        List<CategoryDto> categories =categoryService.getAllCategories();
        BaseResponse<List<CategoryDto>> baseResponse = new BaseResponse<>();
        baseResponse.setData(categories);
          return baseResponse;

    }
}
