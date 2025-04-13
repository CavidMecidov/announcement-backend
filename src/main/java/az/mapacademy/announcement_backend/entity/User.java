package az.mapacademy.announcement_backend.entity;

import az.mapacademy.announcement_backend.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String phone_number;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked;
    private Boolean enabled;
    private LocalDateTime created_date;
    @Column(name = "modified_date")
    private LocalDateTime modified_date;





}
