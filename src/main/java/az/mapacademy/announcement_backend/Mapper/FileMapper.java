package az.mapacademy.announcement_backend.Mapper;


import az.mapacademy.announcement_backend.dto.FileResponse;
import az.mapacademy.announcement_backend.entity.File;
import org.mapstruct.Mapper;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResponse toFileResponse(File file, byte[] data);
}
