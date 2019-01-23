package overview.demo.services;

import overview.demo.models.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> findAll();

    User findOneDyId(int id);


}
