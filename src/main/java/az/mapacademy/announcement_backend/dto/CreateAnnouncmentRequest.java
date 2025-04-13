package az.mapacademy.announcement_backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;

@Data
public class CreateAnnouncmentRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    private String description;

    @NotBlank(message = "AnnouncmentNumber can not be blank")
    private Long announcmentNumber;

    @NotNull(message = "Price can not be null ")
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private Double price;

    @NotNull(message = "Phone number can not be null ")
    @Size(min = 10, max = 10, message = "Phone_number must contain 10 characters")
    @Pattern(regexp = "\\d{10}", message = "Phone_number must contain only digits")
    private String Phone_number;


    private String sales_full_name;
    private Boolean delivery;


    @NotNull(message = " City id can not be null")
    private Long cityid;
    @NotNull(message = " Category id can not be null")
    private Long categoryid;

}
