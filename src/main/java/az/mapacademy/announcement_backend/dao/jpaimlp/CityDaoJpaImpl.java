package az.mapacademy.announcement_backend.dao.jpaimlp;

import az.mapacademy.announcement_backend.Repository.CityRepository;
import az.mapacademy.announcement_backend.dao.CityDao;
import az.mapacademy.announcement_backend.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("cityDaoJpaImpl")
@RequiredArgsConstructor
public class CityDaoJpaImpl implements CityDao {
    private final CityRepository cityRepository;
    @Override
    public List<City> findAll() {
        System.out.println(" Find all cities method is called from Jpa implementation of CityDa");
        return cityRepository.findAll();
    }
}
