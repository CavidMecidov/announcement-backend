package az.mapacademy.announcement_backend.OldMapper;

import az.mapacademy.announcement_backend.dto.CategoryDto;
import az.mapacademy.announcement_backend.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category){
        CategoryDto categoryDto =  new CategoryDto();
        categoryDto.setCategoryid(category.getCategoryid());
        categoryDto.setName(category.getName());
        return  categoryDto;
    }

    public List<CategoryDto> toDtoList(List<Category> categoryList){
         return categoryList.stream()
                .map(this::toDto)
                .toList();

    }
}

