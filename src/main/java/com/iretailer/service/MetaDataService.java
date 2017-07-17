package com.iretailer.service;

import com.iretailer.dao.MetaDataMapper;
import com.iretailer.dao.UserMapper;
import com.iretailer.dto.Location;
import com.iretailer.dto.Site;
import com.iretailer.dto.SiteZone;
import com.iretailer.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
@Service
public class MetaDataService {

    @Autowired
    private MetaDataMapper metaDataMapper;

    public List<Site> query() {
        return metaDataMapper.query();
    }

    public List<Location> queryLocation(){
        Location root = new Location();
        root.setId(0l);
        List<Location> locationList = getLocation(root);
        return locationList;
    }
    private List<Location> getLocation(Location location){
        List<Location> locationList = metaDataMapper.queryLocation(location.getId());
        locationList.stream().forEach(e -> {
            e.setChildren(getLocation(e));
        });
        return locationList;
    }

    public List<SiteZone> querySiteZoneByZiteId(Long id) {
        return metaDataMapper.getSiteZoneBSiteId(id);
    }
}