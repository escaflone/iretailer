package com.iretailer.service;

import com.iretailer.dao.UserMapper;
import com.iretailer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper usermapper;

    public List<User> query() {
        return usermapper.query();
    }

    public int create(User user) {
        return usermapper.create(user);
    }

    public User queryById(int id) {
        return usermapper.queryById(id);
    }

    public User queryUserByUserNameAndPassword(User user) {
        return usermapper.queryUserByUserNameAndPassword(user);
    }
}
