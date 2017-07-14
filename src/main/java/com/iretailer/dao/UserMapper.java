package com.iretailer.dao;

import com.iretailer.dto.User;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
public interface UserMapper {
    public List<User> query();

    public int create(User user);

    User queryById(int id);

    User queryUserByUserName(User user);
}
