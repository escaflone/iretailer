package com.iretailer.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by clat on 2017/5/21.
 */
@Component
public class Constant {
    //常量
    public static String TABLE = "table";
    public static String COLUMN = "column";
    public static String TIME_FORMAT = "yy-MM-dd HH:mm:ss";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
    //查询参数配置
    public static Map<String,String> templateMap = new HashMap<>();

    @PostConstruct
    private void init(){
        templateMap = MapBuilder.build(new HashMap())
                .map("count_in","inout.ftl").map("count_out","inout.ftl")
                .map("count_passby","passby.ftl")
                .map("count_sales","sale.ftl").map("count_goods","sale.ftl").map("count_trades","sale.ftl")
                .get();
    }
    public static String timeFormat(Long l){
        Date date = new Date(l);
        return dateFormat.format(date);
    }
}