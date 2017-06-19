package com.iretailer.dto;

/**
 * Created by wubin on 2017/6/19.
 */
public class pageWidget {
    private Long id;
    private Integer rank;
    private Integer sizex;
    private Integer sizey;
    private String name;
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSizex() {
        return sizex;
    }

    public void setSizex(Integer sizex) {
        this.sizex = sizex;
    }

    public Integer getSizey() {
        return sizey;
    }

    public void setSizey(Integer sizey) {
        this.sizey = sizey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
