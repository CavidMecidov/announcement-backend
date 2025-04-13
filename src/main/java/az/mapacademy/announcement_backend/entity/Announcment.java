package az.mapacademy.announcement_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "annoucnements")
@Entity

public class Announcment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long announcnementid;
private String name;
private String description;
private Long annoouncement_number;
private Double price;
private Boolean delivery;
private LocalDateTime created_date;
private LocalDateTime Modified_date;

@JoinColumn(name = "user_id",referencedColumnName = "user_id")
@ManyToOne
private User user;
@JoinColumn(name = "City_id",referencedColumnName = "City_ID")
@ManyToOne
private City city;

@JoinColumn(name = "Category_id",referencedColumnName = "category_ID")
@ManyToOne
private  Category category;
}
