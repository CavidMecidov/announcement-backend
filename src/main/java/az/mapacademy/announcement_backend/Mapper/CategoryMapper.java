package az.mapacademy.announcement_backend.Mapper;

import az.mapacademy.announcement_backend.dto.CategoryDto;
import az.mapacademy.announcement_backend.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel =  "spring")
public interface CategoryMapper {
    List<CategoryDto> toDtoList(List<Category> categories);
    CategoryDto toDto(Category category);

}
