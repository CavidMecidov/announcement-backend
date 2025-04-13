package az.mapacademy.announcement_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cities")
//snake case--category_id
//camel case- categoryid
public class City {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "City_id")
   private long cityid;
   private String name;






}
