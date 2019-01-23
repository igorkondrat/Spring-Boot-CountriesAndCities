package overview.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import overview.demo.models.University;

public interface UniversitiesDao extends JpaRepository<University,Integer> {
}
