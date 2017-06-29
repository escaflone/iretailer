package com.iretailer.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubin on 2017/6/28.
 */
public class Location {
    private Long id;
    private Long pid;
    private Integer type;
    private Integer getweather;
    private Long rank;
    private String name;
    private String displayName;
    private List<Location> children = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGetweather() {
        return getweather;
    }

    public void setGetweather(Integer getweather) {
        this.getweather = getweather;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Location> getChildren() {
        return children;
    }

    public void setChildren(List<Location> children) {
        this.children = children;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
