package com.iretailer.dao;

import com.iretailer.dto.Location;
import com.iretailer.dto.Site;
import com.iretailer.dto.User;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
public interface MetaDataMapper {
    public List<Site> query();

    List<Location> queryLocation(Long pid);
}
