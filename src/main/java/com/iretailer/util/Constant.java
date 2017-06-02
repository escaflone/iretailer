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
    public static Map<String,Map<String,String>> dataFielsMap = new HashMap<>();
    public static Map<String,String> groupByMap = new HashMap<>();
    //查询sql模板
    public static String SINGLE_TABLE_QUERY_SQL = "select ${column} from ${table} where date_time >= '${st}' and date_time <= '${ed}' group by ${groupBy} ${sortBy}";


    @PostConstruct
    private void init(){
        dataFielsMap.put("exit",MapBuilder.build(new HashMap<String,String>())
                .map(TABLE,"records_inout_partitioned")
                .map(COLUMN,"count_out")
                .get());
        dataFielsMap.put("enter",MapBuilder.build(new HashMap<String,String>())
                .map(TABLE,"records_inout_partitioned")
                .map(COLUMN,"count_in")
                .get());
        dataFielsMap.put("passby",MapBuilder.build(new HashMap<String,String>())
                .map(TABLE,"records_passby_partitioned")
                .map(COLUMN,"count_passby")
                .get());

        groupByMap = MapBuilder.build(new HashMap<String,String>())
                .map("All","date_time")
                .map("0","date").map("5","_5").map("10","_10").map("15","_15").map("30","_30")
                .get();
    }
    public static String timeFormat(Long l){
        Date date = new Date(l);
        return dateFormat.format(date);
    }
}
