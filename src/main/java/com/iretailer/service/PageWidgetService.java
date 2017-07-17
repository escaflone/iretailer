package com.iretailer.service;

import com.iretailer.dao.MetaDataMapper;
import com.iretailer.dto.Location;
import com.iretailer.dto.PageWidget;
import com.iretailer.dto.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
@Service
public class PageWidgetService {

    @Autowired
    private MetaDataMapper metaDataMapper;

    public List<PageWidget> query(Long id) {
        return metaDataMapper.queryPageWidget(id);
    }

    public void update(PageWidget pageWidget) {
        metaDataMapper.updatePageWidget(pageWidget);
    }

    @Transactional
    public void delete(PageWidget pageWidget, Long userid){
        deleteUserPageWidget(userid,pageWidget.getId());
        metaDataMapper.deletePageWidget(pageWidget);
    }

    public void create(PageWidget pageWidget) {
        metaDataMapper.createPageWidget(pageWidget);
    }

    public void deleteUserPageWidget(Long userid, Long pwid){
        metaDataMapper.deleteUserPageWidget(userid,pwid);
    }
}