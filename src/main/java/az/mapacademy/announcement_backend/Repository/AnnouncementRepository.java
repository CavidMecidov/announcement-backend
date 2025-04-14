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
       select a1 from Announcment a1 
       where a1.name like concat('%', :name, '%') 
       and a1.description like concat('%', :description, '%')
       """)
     Page<Announcment> findAllWithJpql(@Param("name")String name, @Param("description")String description, Pageable pageable);

    @Query(value = """
       select * from annoucnements
       where name like concat('%', :name, '%') 
       and description like concat('%', :description, '%')
       """, nativeQuery = true)

     Page<Announcment> findAllWithSql(@Param("name")String name, @Param("description")String description, Pageable pageable);

}
