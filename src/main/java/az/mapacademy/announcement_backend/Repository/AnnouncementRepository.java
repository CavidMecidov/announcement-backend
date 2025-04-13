package az.mapacademy.announcement_backend.Repository;

import az.mapacademy.announcement_backend.entity.Announcment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcment, Long> {

    List<Announcment> findAllByNameContainingAndDescription( String name, String description, Pageable pageable);
     @Query("""
             select a from Announcment 
            a where a.name like '%' || :name || '%'
            and a.description like '%' || :description|| '% """)
     Page<Announcment> findAllWithJpql(@Param("name")String name, @Param("description")String description, Pageable pageable);

     @Query(value = """ 
             select * from announcments 
             where  like concat( '%' :name,'%')
            and  like concat( '%' :description,'%')"""
     ,nativeQuery = true)

     Page<Announcment> findAllWithSql(@Param("name")String name, @Param("description")String description, Pageable pageable);

}
