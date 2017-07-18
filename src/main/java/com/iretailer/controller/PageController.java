package com.iretailer.controller;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.dto.PageWidget;
import com.iretailer.service.PageWidgetService;
import com.iretailer.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
@RestController
@RequestMapping(value="apis/pageWidget")
public class PageController extends BaseController{

    @Autowired
    PageWidgetService pwService;

    @RequestMapping(value="" , method = RequestMethod.GET)
    public List<PageWidget> query(HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        return pwService.query(userId);
    }
    @RequestMapping(value="" , method = RequestMethod.PUT)
    public void update(@RequestBody PageWidget pageWidget){
        pwService.update(pageWidget);
    }
    @RequestMapping(value="" , method = RequestMethod.DELETE)
    public void delete(@RequestBody PageWidget pageWidget,HttpSession httpSession){
        Long userId = getUserIdFromSession(httpSession);
        pwService.delete(pageWidget,userId);
    }
    @RequestMapping(value="" , method = RequestMethod.POST)
    public void create(@RequestBody PageWidget pageWidget,HttpSession httpSession){
        pwService.create(pageWidget,getUserIdFromSession(httpSession));
    }
}