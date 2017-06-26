package com.iretailer.dto;

/**
 * Created by wubin on 2017/6/26.
 */
public class Location {
    private Long id;
    private Long quareId;
    private String quareName;
    private String provinceId;
    private String provinceName;
    private Long cityId;
    private String cityName;
    private Long countryId;
    private String countryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuareId() {
        return quareId;
    }

    public void setQuareId(Long quareId) {
        this.quareId = quareId;
    }

    public String getQuareName() {
        return quareName;
    }

    public void setQuareName(String quareName) {
        this.quareName = quareName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
