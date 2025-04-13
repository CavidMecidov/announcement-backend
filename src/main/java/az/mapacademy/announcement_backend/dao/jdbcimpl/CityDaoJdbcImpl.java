package az.mapacademy.announcement_backend.dao.jdbcimpl;


import az.mapacademy.announcement_backend.Config.DatabaseConfig;
import az.mapacademy.announcement_backend.Constant.QueryConstants;
import az.mapacademy.announcement_backend.dao.CityDao;
import az.mapacademy.announcement_backend.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("cityDaoJdbcImpl")
@Slf4j
public class CityDaoJdbcImpl implements CityDao {
    @Override
    public List<City> findAll() {
        log.info("Get cities from DB");
        List<City> cities = new ArrayList<>();
        //try-with resourche
        try (Connection connection = DatabaseConfig.getConnection();) {
            Statement statement = connection.createStatement();
            log.info("Get cities from DB  query: {}" ,QueryConstants.GET_CITY_LIST_QUERY);
            ResultSet resultSet = statement.executeQuery(QueryConstants.GET_CITY_LIST_QUERY);
            while (resultSet.next()) {
                Long id = resultSet.getLong("city_id");
                String name = resultSet.getString("Name");
                City city = new City(id, name);
                cities.add(city);
            }

        } catch (SQLException e) {
            throw new  RuntimeException(e);
        }
        return cities;
    }

    public static void main(String[] args) {
        CityDaoJdbcImpl citydao = new CityDaoJdbcImpl();
        List<City> cities = citydao.findAll();
        System.out.println(cities);
    }

}

