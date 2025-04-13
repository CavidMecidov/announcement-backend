package az.mapacademy.announcement_backend.Mapper;

import az.mapacademy.announcement_backend.dto.CityDto;
import az.mapacademy.announcement_backend.entity.City;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel =  "spring")
public interface CityMapper {
    List<CityDto> toDtoList (List<City> cities);
    CityDto toDto (City city);

}
