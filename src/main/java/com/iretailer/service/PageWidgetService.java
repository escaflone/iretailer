package com.iretailer.service;

import com.iretailer.dao.MetaDataMapper;
import com.iretailer.dto.Location;
import com.iretailer.dto.PageWidget;
import com.iretailer.dto.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
@Service
public class PageWidgetService {

    @Autowired
    private MetaDataMapper metaDataMapper;

    public List<PageWidget> query() {
        return metaDataMapper.queryPageWidget();
    }

    public void update(PageWidget pageWidget) {
        metaDataMapper.updatePageWidget(pageWidget);
    }

    public void delete(PageWidget pageWidget) {
        metaDataMapper.deletePageWidget(pageWidget);
    }

    public void create(PageWidget pageWidget) {
        metaDataMapper.createPageWidget(pageWidget);
    }
}