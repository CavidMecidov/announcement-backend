package az.mapacademy.announcement_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class AnnouncmentResponse {
    private Long announcnementid;
    private String name;
    private String description;
    private Long annoouncement_number;
    private Double price;
    private String Phone_number;
    private String sales_full_name;
    private Boolean delivery;
    private LocalDateTime creadet_date;
    private LocalDateTime Modified_date;
    private CityDto city;
    private CategoryDto category;

}
