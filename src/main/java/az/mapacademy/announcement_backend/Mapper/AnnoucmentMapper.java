package az.mapacademy.announcement_backend.Mapper;

import az.mapacademy.announcement_backend.dto.CreateAnnouncmentRequest;
import az.mapacademy.announcement_backend.dto.AnnouncmentResponse;
import az.mapacademy.announcement_backend.dto.UpdateAnnouncmentRequest;
import az.mapacademy.announcement_backend.entity.Announcment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnnoucmentMapper {

    List<AnnouncmentResponse> toResponseList(List<Announcment> announcments);

    AnnouncmentResponse toDto(Announcment announcment);

    @Mapping(target = "announcement_number", expression = "java(generatedAnnouncementNumber() )")
    @Mapping(source = "cityid", target = "city.cityid")
    @Mapping(source = "categoryid", target = "category.categoryid")
    @Mapping(target= "created_date",expression = "java(getNow())")
    @Mapping(target= "modified_date",expression = "java(getNow())")
    Announcment toEntity(CreateAnnouncmentRequest request);

    Announcment toEntity(Long announcmentid, UpdateAnnouncmentRequest announcmentRequest);

    void populate(UpdateAnnouncmentRequest request, @MappingTarget Announcment announcment);

    default Long generatedAnnouncementNumber() {
        double d = Math.random() * 1000000;
        return (long) d;
    }

    default LocalDateTime getNow(){
    return  LocalDateTime.now();
    }

}
