package overview.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import overview.demo.models.City;

public interface CitiesDao extends JpaRepository<City,Integer> {
    @Query("select c from City c where c.nameCity=:nameCity")
    City findByName(@Param("nameCity") String nameCity);
}
