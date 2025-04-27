package az.mapacademy.announcement_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAnnouncmentRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    private String description;
    @NotNull(message = "Price can not be null ")
    @Min(value = 0, message = "Price must be than or equal zero")

    private Double price;
    private Boolean delivery;
}