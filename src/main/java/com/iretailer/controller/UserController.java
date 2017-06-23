package com.iretailer.controller;

import com.iretailer.dto.User;
import com.iretailer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
@RestController
@RequestMapping(value="user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="" , method = RequestMethod.GET)
    public List<User> query(User user){
        return userService.query();
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public User create(User user){
        int id = userService.create(user);
        return user;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public User login(User user){
        int id = userService.queryUserByUserNameAndPassword(user);
        return user;
    }
}