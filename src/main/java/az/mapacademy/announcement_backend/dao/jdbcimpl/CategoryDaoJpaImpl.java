package az.mapacademy.announcement_backend.dao.jdbcimpl;

import az.mapacademy.announcement_backend.Repository.CategoryRepository;
import az.mapacademy.announcement_backend.dao.CategoryDao;
import az.mapacademy.announcement_backend.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository("categoryDaoJpaImpl")
public class CategoryDaoJpaImpl implements CategoryDao {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        System.out.println(" Find all categories method is called from Jpa implementation of CategoryDao");
        return categoryRepository.findAll();
    }
}
