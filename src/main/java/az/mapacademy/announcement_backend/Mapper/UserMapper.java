package az.mapacademy.announcement_backend.Mapper;

import az.mapacademy.announcement_backend.dto.UserRequestRegister;
import az.mapacademy.announcement_backend.dto.UserRequestUpdate;
import az.mapacademy.announcement_backend.dto.UserResponse;
import az.mapacademy.announcement_backend.entity.User;
import az.mapacademy.announcement_backend.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,// nul gelen deyerleri qebul elemir evvelki deyerini saxliyir
   imports = Role.class)
public interface UserMapper {
    UserResponse toResponse(User user);

    @Mapping(target = "created_date", expression = "java(getNow())")
    @Mapping(target = "modified_date", expression = "java(getNow())")
    @Mapping(target = "locked", constant = "false")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "role", expression = "java(Role.USER)")
    User toUser(UserRequestRegister register);

    @Mapping(target = "modified_date", expression = "java(getNow())")
    void populate(UserRequestUpdate request, @MappingTarget User user);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
