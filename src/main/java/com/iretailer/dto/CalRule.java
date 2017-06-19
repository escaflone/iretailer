package com.iretailer.dto;

import java.util.List;

/**
 * Created by wubin on 2017/6/19.
 */
public class CalRule {
    private String name;
    private String[] need;
    private String express;

    public CalRule(String express, String[] need){
        this.express = express;
        this.need = need;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNeed() {
        return need;
    }

    public void setNeed(String[] need) {
        this.need = need;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }
}
