package overview.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import overview.demo.dao.UserDao;
import overview.demo.models.User;
import overview.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        if (user != null) {
            userDao.save(user);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userDao.findAll();
        if (userList == null){
            return new ArrayList<>();
        }
        return userList;
    }

    @Override
    public User findOneDyId(int id) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
