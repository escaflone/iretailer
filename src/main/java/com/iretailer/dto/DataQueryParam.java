package com.iretailer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clat on 2017/5/8.
 */
public class DataQueryParam {
    List<String> dataFields = new ArrayList<>();
    List<Integer> siteIdList = new ArrayList<>();
    List<Integer> siteZoneList = new ArrayList<>();
    Long startTime;
    Long endTime;
    String zoneTypeId;
    GroupBy groupBy = new GroupBy();
    Map<String, Integer> sortBy = new HashMap<>();
    List<Integer> limit;


    public List<String> getDataFields() {
        return dataFields;
    }

    public void setDataFields(List<String> dataFields) {
        this.dataFields = dataFields;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getZoneTypeId() {
        return zoneTypeId;
    }

    public void setZoneTypeId(String zoneTypeId) {
        this.zoneTypeId = zoneTypeId;
    }


    public Map<String, Integer> getSortBy() {
        return sortBy;
    }

    public void setSortBy(Map<String, Integer> sortBy) {
        this.sortBy = sortBy;
    }

    public List<Integer> getLimit() {
        return limit;
    }

    public void setLimit(List<Integer> limit) {
        this.limit = limit;
    }


    public GroupBy getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupBy groupBy) {
        this.groupBy = groupBy;
    }

    public List<Integer> getSiteIdList() {
        return siteIdList;
    }

    public void setSiteIdList(List<Integer> siteIdList) {
        this.siteIdList = siteIdList;
    }

    public List<Integer> getSiteZoneList() {
        return siteZoneList;
    }

    public void setSiteZoneList(List<Integer> siteZoneList) {
        this.siteZoneList = siteZoneList;
    }
}