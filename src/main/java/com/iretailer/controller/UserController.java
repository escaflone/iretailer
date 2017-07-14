package com.iretailer.controller;

import com.iretailer.controller.response.ErrorMessage;
import com.iretailer.controller.response.RestResponse;
import com.iretailer.dto.User;
import com.iretailer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
@RestController
@RequestMapping(value="/apis")
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
    public RestResponse login(@RequestBody User user, HttpSession httpSession){
        RestResponse<String> result = new RestResponse<>();
        try{
            User userDB = userService.queryUserByUserNameAndPassword(user);
            if(userDB == null){
                result.setCode(ErrorMessage.NOT_EXIST.getCode());
                result.setMsg(MessageFormat.format(ErrorMessage.NOT_EXIST.getMsg(),user.getUserName()));
            }else{
                httpSession.setAttribute("userId",userDB.getUserId());

            }
        }catch(Exception e){
            e.printStackTrace();
            result.setCode(ErrorMessage.SYSTEM_ERROR.getCode());
            result.setMsg(MessageFormat.format(ErrorMessage.SYSTEM_ERROR.getMsg(), e.getMessage()));
        }
        return result;
    }
}