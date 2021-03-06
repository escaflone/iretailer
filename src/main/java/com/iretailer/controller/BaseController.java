package com.iretailer.controller;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.service.BaseService;
import com.iretailer.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by clat on 2017/5/8.
 */
@RestController
@RequestMapping(value="/apis")
public class BaseController {

    @Autowired
    BaseService baseService;

    @RequestMapping(value="/query" , method = RequestMethod.POST)
    public List query(@RequestBody DataQueryParam dqp){
        return baseService.query(dqp);
    }

    public Long getUserIdFromSession(HttpSession httpSession){
        return (Long) httpSession.getAttribute(Constant.USER_ID);
    }
}