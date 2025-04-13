package az.mapacademy.announcement_backend.OldMapper;

import az.mapacademy.announcement_backend.dto.CityDto;
import az.mapacademy.announcement_backend.entity.City;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Data
@Component
public class CityMapper {
    public CityDto toDto(City city) {
        CityDto citydto = new CityDto();
        citydto.setCityiid(city.getCityid());
        citydto.setName(city.getName());
        return citydto;
    }

    public List<CityDto> toDtoList(List<City> cities) {
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city : cities) {
            CityDto cityDto = toDto(city);
            cityDtoList.add(cityDto);


        }
        return cityDtoList;
    }

}