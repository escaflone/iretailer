package com.iretailer.dto;

import java.util.List;

/**
 * Created by wubin on 2017/6/26.
 */
public class Site {
    private Long id;
    private Location location;
    private Long parentId;
    private Integer type;
    private Integer rank;
    private Integer disabled;
    private String name;
    private String displayName;
    private Integer operationAcreage;
    private String description;
    private String coordinate;
    private String address;
    private List<SiteZone> siteZoneList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getOperationAcreage() {
        return operationAcreage;
    }

    public void setOperationAcreage(Integer operationAcreage) {
        this.operationAcreage = operationAcreage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<SiteZone> getSiteZoneList() {
        return siteZoneList;
    }

    public void setSiteZoneList(List<SiteZone> siteZoneList) {
        this.siteZoneList = siteZoneList;
    }
}
