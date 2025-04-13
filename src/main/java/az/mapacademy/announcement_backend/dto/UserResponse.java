package az.mapacademy.announcement_backend.dto;

import az.mapacademy.announcement_backend.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private long user_id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phone_number;
    private String email;
    private String username;
    private Role role;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:SS")
    private LocalDateTime created_date;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:SS")
    private LocalDateTime modified_date;


}
