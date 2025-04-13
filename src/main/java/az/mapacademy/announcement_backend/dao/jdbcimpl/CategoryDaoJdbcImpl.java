package az.mapacademy.announcement_backend.dao.jdbcimpl;

import az.mapacademy.announcement_backend.Config.DatabaseConfig;
import az.mapacademy.announcement_backend.Constant.QueryConstants;
import az.mapacademy.announcement_backend.dao.CategoryDao;
import az.mapacademy.announcement_backend.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository("categoryDaoJdbcImpl")
@Slf4j
public class CategoryDaoJdbcImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
      log.info("Get categories from DB");
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();) {
            Statement statement = connection.createStatement();
            log.info("Get categories from DB query: {}",QueryConstants.GET_CATEGORY_LIST_QUERY);
            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_CATEGORY_LIST_QUERY);
            while ((resultSet.next())) {
                Long id = resultSet.getLong("category_ID");
                String name = resultSet.getString("Name");
                Category category = new Category(id, name);
                categories.add(category);
            }


        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
        return categories;
    }



}
