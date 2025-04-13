package az.mapacademy.announcement_backend.dao;

import az.mapacademy.announcement_backend.entity.City;

import java.util.List;

public interface CityDao {
    List<City> findAll();
}
