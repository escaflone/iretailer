package com.iretailer.controller;

import com.iretailer.dto.Location;
import com.iretailer.dto.Site;
import com.iretailer.dto.User;
import com.iretailer.service.MetaDataService;
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
@RequestMapping(value="apis/meta")
public class MetaDataController {

    @Autowired
    private MetaDataService metaDataService;

    @RequestMapping(value="" , method = RequestMethod.GET)
    public List<Site> query(){
        return metaDataService.query();
    }
    @RequestMapping(value="location" , method = RequestMethod.GET)
    public List<Location> queryLocation(){
        return metaDataService.queryLocation();
    }
}