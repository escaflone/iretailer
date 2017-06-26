package com.iretailer.dto;

/**
 * Created by clat on 2017/5/23.
 */
public class GroupBy {
    private String period = "d";
    private String domain;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
