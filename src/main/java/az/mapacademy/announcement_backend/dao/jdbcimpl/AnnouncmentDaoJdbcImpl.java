package az.mapacademy.announcement_backend.dao.jdbcimpl;


import az.mapacademy.announcement_backend.Config.DatabaseConfig;
import az.mapacademy.announcement_backend.Constant.QueryConstants;
import az.mapacademy.announcement_backend.dao.AnnouncmentDao;
import az.mapacademy.announcement_backend.entity.Announcment;
import az.mapacademy.announcement_backend.entity.Category;
import az.mapacademy.announcement_backend.entity.City;
import az.mapacademy.announcement_backend.enums.SortDirection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository("announcmentDaoJdbcImpl")
public class AnnouncmentDaoJdbcImpl implements AnnouncmentDao {
    @Override
    public Page<Announcment> findAll(int page, int size, SortDirection sortCreatedDat,String name,String description) {
        log.info("Get annoucment from DB");
        List<Announcment> announcments = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcment from DB query: {}", QueryConstants.GET_ANNOUNCMENT_LIST_QUERY);
            int offset = size * (page - 1);

            PreparedStatement statement = connection.prepareStatement(QueryConstants.GET_ANNOUNCMENT_LIST_QUERY);
            String query = """
                    SELECT
                    a.annoucnement_ID,
                    a.Name,
                    a.Description,
                    a.announcement_number,
                    a.PRICE,
                    a.Phone_number,
                    a.Sales_full_name,
                    a.delivery,
                    a.Category_id,
                    a.Creadet_date,
                    a.Modified_date,
                    c.City_id ,
                    c.NAME as city_name,
                    ct.category_ID,
                    ct.Name as category_name
                    FROM annoucnements a
                    LEFT JOIN cities c ON a.City_id = c.City_ID
                    LEFT JOIN categories ct ON a.Category_id = ct.category_ID
                    LIMIT ? OFFSET ? ;""";
            statement.setInt(1, size);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Announcment announcment = new Announcment();
                announcment.setAnnouncnementid(resultSet.getLong("annoucnement_ID"));
                announcment.setName(resultSet.getString("Name"));
                announcment.setDescription(resultSet.getString("Description"));
                announcment.setAnnoouncement_number(resultSet.getLong("announcement_number"));
                announcment.setPrice(resultSet.getDouble("PRICE"));
                announcment.setPhone_number(resultSet.getString("Phone_number"));
                announcment.setSales_full_name(resultSet.getString("Sales_full_name"));
                announcment.setDelivery(resultSet.getBoolean("Delivery"));

                Timestamp createDate = resultSet.getTimestamp("Creadet_date");
                LocalDateTime localDateTimeCreated = createDate.toLocalDateTime();
                announcment.setCreadet_date(localDateTimeCreated);


                Timestamp ModifiedDate = resultSet.getTimestamp("Modified_date");
                LocalDateTime localDateTimeModified = createDate.toLocalDateTime();
                announcment.setModified_date(localDateTimeModified);

                Long cityId = resultSet.getLong("City_id");
                String cityName = resultSet.getString("city_name");
                City city = new City(cityId, cityName);
                announcment.setCity(city);

                Long categoryId = resultSet.getLong("Category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                announcment.setCategory(category);
                announcments.add(announcment);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Page.empty();
    }
@Override
    public void create(Announcment announcment) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Create announcment Query: {}", QueryConstants.CREATE_ANNOUNCMENT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.CREATE_ANNOUNCMENT_QUERY);
            preparedStatement.setString(1, announcment.getName());
            preparedStatement.setString(2, announcment.getDescription());
            preparedStatement.setLong(3, announcment.getAnnoouncement_number());
            preparedStatement.setDouble(4, announcment.getPrice());
            preparedStatement.setString(5, announcment.getPhone_number());
            preparedStatement.setString(6, announcment.getSales_full_name());
            preparedStatement.setBoolean(7, announcment.getDelivery());
            preparedStatement.setLong(8, announcment.getCity() != null ? announcment.getCity().getCityid() : null);
            preparedStatement.setLong(9, announcment.getCategory() != null ? announcment.getCategory().getCategoryid() : null);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
@Override
    public void update(Announcment announcment) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Update announcment Query: {}", QueryConstants.UPDATE_ANNOUNCMENT_QUERY);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.UPDATE_ANNOUNCMENT_QUERY);
            preparedStatement.setString(1, announcment.getName());
            preparedStatement.setString(2, announcment.getDescription());
            preparedStatement.setDouble(3, announcment.getPrice());
            preparedStatement.setString(4, announcment.getSales_full_name());
            preparedStatement.setBoolean(5, announcment.getDelivery());
            preparedStatement.setLong(6, announcment.getAnnouncnementid());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Announcment> findById(Long announcementId) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcement by id query: {}", QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            PreparedStatement preparedStatement = connection.prepareStatement(QueryConstants.GET_ANNOUNCEMENT_BY_ID);
            preparedStatement.setLong(1, announcementId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Announcment announcement = new Announcment();
                announcement.setAnnouncnementid(resultSet.getLong("announcement_id"));
                announcement.setName(resultSet.getString("name"));
                announcement.setDescription(resultSet.getString("description"));
                announcement.setAnnouncnementid(resultSet.getLong("announcement_number"));
                announcement.setPrice(resultSet.getDouble("price"));
//                announcement.setPhoneNumber(resultSet.getString("phone_number"));
//                announcement.setSellerFullName(resultSet.getString("seller_full_name"));
                announcement.setDelivery(resultSet.getBoolean("delivery"));

                Timestamp createDate = resultSet.getTimestamp("created_date");
                LocalDateTime createdDateTime = createDate.toLocalDateTime();
                announcement.setCreadet_date(createdDateTime);

                Timestamp modifiedDate = resultSet.getTimestamp("modified_date");
                LocalDateTime modifiedDateTime = modifiedDate.toLocalDateTime();
                announcement.setModified_date(modifiedDateTime);

                Long cityId = resultSet.getLong("city_id");
                String cityName = resultSet.getString("city_name");
                City city = new City(cityId, cityName);
                announcement.setCity(city);

                Long categoryId = resultSet.getLong("category_id");
                String categoryName = resultSet.getString("category_name");
                Category category = new Category(categoryId, categoryName);
                announcement.setCategory(category);

                return Optional.of(announcement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }


    @Override
    public Integer getAnnouncmentTotalCount() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            log.info("Get announcment totalCount Query: {}", QueryConstants.GET_ANNOUNCMENT_COUNT_QUERY);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_ANNOUNCMENT_COUNT_QUERY);
            if (resultSet.next()) {
                return resultSet.getInt("total_Count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
