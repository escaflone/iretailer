package com.iretailer.util;

import com.iretailer.dto.CalRule;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by clat on 2017/5/21.
 */
@Component
public class Constant {
    //常量
    public static String TABLE = "table";
    public static String COLUMN = "column";
    public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
    //查询参数配置
    public static Map<String,String> templateMap = new HashMap<>();
    public static Map<String,CalRule> calMap = new HashMap<>();

    @PostConstruct
    private void init(){
        templateMap = MapBuilder.build(new HashMap())
                .map("count_in","inout.ftl").map("count_out","inout.ftl")
                .map("count_passby","passby.ftl")
                .map("count_sales","sale.ftl").map("count_goods","sale.ftl").map("count_trades","sale.ftl")
                .get();
        //TODO 将来为 读取 数据库
        calMap = MapBuilder.build(new HashMap())
                .map("conversationRate",    new CalRule("(count_trades/count_in) conversationRate", new String[]{"count_in","count_trades"}))
                .map("acv",                 new CalRule("(count_sales/count_in) acv", new String[]{"count_sales","count_in"}))
                .map("atv",                 new CalRule("(count_sales/count_trades) atv", new String[]{"count_sales","count_trades"}))
                .map("upt",                 new CalRule("(count_goods/count_trades) upt", new String[]{"count_goods","count_trades"}))
                .map("occupancy",           new CalRule("(count_in/count_out) occupancy", new String[]{"count_in","count_out"}))
                .map("uaa",                 new CalRule("(count_in/area) uaa", new String[]{"count_in","area"}))
                .get();
    }
    public static String timeFormat(Long l){
        Date date = new Date(l);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return dateFormat.format(date);
    }
}