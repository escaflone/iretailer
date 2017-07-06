package com.iretailer.controller;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.dto.PageWidget;
import com.iretailer.service.PageWidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
@RestController
@RequestMapping(value="apis/pageWidget")
public class PageController {

    @Autowired
    PageWidgetService pwService;

    @RequestMapping(value="" , method = RequestMethod.GET)
    public List<PageWidget> query(){
        return pwService.query();
    }
    @RequestMapping(value="" , method = RequestMethod.PUT)
    public void update(@RequestBody PageWidget pageWidget){
        pwService.update(pageWidget);
    }
    @RequestMapping(value="" , method = RequestMethod.DELETE)
    public void delete(@RequestBody PageWidget pageWidget){
        pwService.delete(pageWidget);
    }
    @RequestMapping(value="" , method = RequestMethod.POST)
    public void create(@RequestBody PageWidget pageWidget){
        pwService.create(pageWidget);
    }
}