package az.mapacademy.announcement_backend.Service;

import az.mapacademy.announcement_backend.Mapper.CityMapper;
import az.mapacademy.announcement_backend.dao.CityDao;
import az.mapacademy.announcement_backend.dao.jdbcimpl.CityDaoJdbcImpl;
import az.mapacademy.announcement_backend.dto.CityDto;
import az.mapacademy.announcement_backend.entity.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service

public class CityService {
private  final CityDao cityDao;

    public CityService(
            @Qualifier("cityDaoJpaImpl") CityDao cityDao,
            CityMapper cityMapper) {
        this.cityDao = cityDao;
        this.cityMapper = cityMapper;
    }

    private  final CityMapper cityMapper;
    public List<CityDto> getAll(){
        log.info("Getting all cities");
        List<City> cities = cityDao.findAll();
        log.info("Cities gott: {}",cities);
        return cityMapper.toDtoList(cities);
    }
}
