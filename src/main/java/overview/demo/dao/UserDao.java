package overview.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import overview.demo.models.User;

public interface UserDao extends JpaRepository<User,Integer> {
    UserDetails findByUsername(String username);
}
