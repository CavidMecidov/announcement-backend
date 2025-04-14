package az.mapacademy.announcement_backend.Service;



import az.mapacademy.announcement_backend.Mapper.CategoryMapper;
import az.mapacademy.announcement_backend.dao.CategoryDao;
import az.mapacademy.announcement_backend.dao.jdbcimpl.CategoryDaoJdbcImpl;
import az.mapacademy.announcement_backend.dto.CategoryDto;
import az.mapacademy.announcement_backend.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class CategoryService {
    private final CategoryDao categoryDao ;
    private final CategoryMapper categoryMapper ;

    public CategoryService(
            @Qualifier("categoryDaoJpaImpl") CategoryDao categoryDao, CategoryMapper categoryMapper) {
        this.categoryDao = categoryDao;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories(){
      log.info("Getting all categories");
        List<Category> categories =  categoryDao.findAll();
        log.info("Got categories: {}", categories);
        return  categoryMapper.toDtoList(categories);

    }
}
