package com.iretailer.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clat on 2017/5/8.
 */
public class DataQueryParam implements Cloneable{
    List<String> dataFields = new ArrayList<>();

    /**
     * 互斥
     * */
    List<Integer> siteIdList = new ArrayList<>();
    List<Integer> siteZoneList = new ArrayList<>();
    String location;
    String siteType;


    Long startTime;
    Long endTime;
    GroupBy groupBy = new GroupBy();
    Map<String, Integer> sortBy = new HashMap<>();
    List<Integer> limit = new ArrayList<>();
    Integer returnType = 1;
    List<String> relations = new ArrayList<>();
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
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

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public List<String> getRelations() {
        return relations;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }
}