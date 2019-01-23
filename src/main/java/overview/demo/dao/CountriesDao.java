package overview.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import overview.demo.models.Country;

import java.util.List;

public interface CountriesDao extends JpaRepository<Country,Integer> {
    @Query("select c from Country c where c.nameCountry=:nameCountry")
    Country findByName(@Param("nameCountry") String nameCountry);
    @Query("select c from Country c where c.politicalSystem=:politicalSystem")
    List<Country> findByPoliticalSystem(@Param("politicalSystem") String politicalSystem);
    @Query("select c from Country c where c.continent=:continent")
    List<Country> findByContinent(@Param("continent") String continent);
}
