package com.iretailer.dao;

import com.iretailer.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wubin on 2017/6/23.
 */
public interface MetaDataMapper {
    public List<Site> query();

    List<Location> queryLocation(Long pid);

    List<PageWidget> queryPageWidget(Long userid);

    int updatePageWidget(PageWidget pageWidget);

    int deletePageWidget(PageWidget pageWidget);

    int createPageWidget(PageWidget pageWidget);

    List<SiteZone> getSiteZoneBSiteId(Long sid);

    void deleteUserPageWidget(@Param("userId")Long userid, @Param("pwId")Long pwid);

    void createUserPageWidget(@Param("userId")Long userid, @Param("pwId")Long pwid);
}
