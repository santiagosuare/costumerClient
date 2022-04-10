package com.costumerClient.costumerClient.Controllers;

import com.costumerClient.costumerClient.dao.UserDao;
import com.costumerClient.costumerClient.models.User;
import com.costumerClient.costumerClient.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;


    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Ramiro");
        user.setLastname("Suarez");
        user.setEmail("santiago.suarez@gmail.com");
        user.setPhone("351443255");

        return user;
    }

    private boolean checkToken(String token){
        String userId = jwtUtil.getKey(token);
        return userId != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.register(user);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader( value= "Authorization") String token) {
        if (!checkToken(token)) {
            return null;
        }
        return userDao.getUsers();
    }


    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader( value= "Authorization") String token,
                       @PathVariable Long id) {
        if (!checkToken(token)) {
            return;
        }
        userDao.delete(id);
    }
}
