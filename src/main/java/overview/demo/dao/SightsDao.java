package overview.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import overview.demo.models.Sight;

public interface SightsDao extends JpaRepository<Sight,Integer> {
    @Query("select c from Sight c where c.nameSight=:nameSight")
    Sight findByName(@Param("nameSight") String nameSight);
}
