package az.mapacademy.announcement_backend.dto;

import az.mapacademy.announcement_backend.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class UserRequestRegister {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "SurName can not be blank")
    private String surname;
    @JsonFormat(pattern = "dd-MM-yy")
    private LocalDate birthdate;
    @NotNull(message = "Phone number can not be null ")
    @Size(min = 10, max = 10, message = "Phone_number must contain 10 characters")
    @Pattern(regexp = "\\d{10}", message = "Phone_number must contain only digits")
    private String phone_number;
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "UserName can not be blank")
    private String username;
    @NotBlank(message = "Password can not be blank")
    private String password;

}
