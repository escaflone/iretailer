package com.iretailer.controller;

import com.iretailer.dto.DataQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
@RestController
@RequestMapping(value="pageWidget")
public class pageController {
    @RequestMapping(value="/query" , method = RequestMethod.POST)
    public List query(){
        return null;
    }
}