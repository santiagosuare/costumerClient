package com.costumerClient.costumerClient.Controllers;

import com.costumerClient.costumerClient.dao.UserDao;
import com.costumerClient.costumerClient.models.User;
import com.costumerClient.costumerClient.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLogin = userDao.getUserAndCredentials(user);
       if (userLogin != null) {
           String tokenJwt = jwtUtil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());
           return tokenJwt;
       }
       return "FAIL";
    }

}
