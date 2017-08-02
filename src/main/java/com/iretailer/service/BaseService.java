package com.iretailer.service;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.dto.TimeRelation;
import com.iretailer.util.Constant;
import com.iretailer.util.MapBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.*;

import static com.iretailer.util.Constant.calMap;

/**
 * Created by clat on 2017/5/8.
 */
@Service
public class BaseService {

    @Autowired
    BasicDataSource dbcp;
    private static String COLUMN = "column";
    private static String DATA = "data";


    public List query(DataQueryParam dqp) {
        List<Map> result = new ArrayList<>();
        result.add(handler(dqp));
        dqp.getRelations().parallelStream().forEach(offset->{
            DataQueryParam _dqp = new DataQueryParam();
            BeanUtils.copyProperties(dqp,_dqp);
            _dqp.setStartTime(dqp.getStartTime()  +  offset);
            _dqp.setEndTime(dqp.getEndTime()  +  offset);
            result.add(handler(_dqp));
        });
        return result;
    }

    private Map handler(DataQueryParam dqp) {
        Map<String, String> columnsMap = new HashMap<>();
//        String templateName = null;
        for(String e : dqp.getDataFields()){
//            if(templateName == null) templateName = Constant.templateMap.get(e);
            /**
             * 添加需要额外计算的指标
             * */
            if(calMap.get(e)!=null){
                for(String need : calMap.get(e).getNeed()){
                    columnsMap.put(need,"");
                }
            }
            columnsMap.put(e, "");
        }
        Long timeos = dqp.getTimeos();
        Long endTime = dqp.getEndTime();
        if(timeos!=null){
            endTime= dqp.getStartTime() + timeos;
        }
        String st = Constant.timeFormat(dqp.getStartTime());
        String ed = Constant.timeFormat(endTime);

        String groupBy = dqp.getPeriod();

        Map<String, Object> params = new HashMap<>();
        params.put("column", columnsMap);
        params.put("st", st);
        params.put("ed", ed);
        params.put("groupBy", groupBy);

        if(dqp.getSiteIdList().size() > 0) {
            params.put("siteid", getSiteIds(dqp.getSiteIdList()));
            params.put("sidlist",dqp.getSiteIdList());
        }else if(dqp.getSiteZoneList().size() > 0) {
            params.put("sitezoneid",getSiteIds(dqp.getSiteZoneList()));
            params.put("sidlist",dqp.getSiteZoneList());
        }
        if(dqp.getLocation() != null){
            params.put("location",dqp.getLocation());
        }else if(dqp.getSiteType() != null){
            params.put("site_type",dqp.getSiteType());
        }

        if(dqp.getLimit().size()==2){
            List<Integer> list= dqp.getLimit();
            params.put("limit",list.get(0)+","+list.get(1));
        }else if(dqp.getLimit().size()==1){
            params.put("limit",dqp.getLimit().get(0));
        }
        params.put("split",dqp.getSplit());

        String sql = getFreeMarkTemplate("result_1table.ftl", params);
        System.out.println(sql);
        Map result = query(sql,dqp.getReturnType());
        return result;
    }

    private Map query(String sql, Integer resultType) {
        Map result = null;
        try(Connection conn = dbcp.getConnection()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (resultType == 1) {
                result = parseResultSetToArray(rs);
            } else {
                result = parseResultSetToMaprs(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    private Map parseResultSetToMaprs(ResultSet rs) throws SQLException {
        Map<String, List> result = new HashMap<>();
        result.put(DATA, new ArrayList<Map<String, String>>());
        result.put(COLUMN, new ArrayList<String>());
        ResultSetMetaData metaData = rs.getMetaData();
        int columnSize = metaData.getColumnCount();
        //TODO 如果结果集为空 做判断
        for (int i = 1; i <= columnSize; i++) {
            result.get(COLUMN).add(metaData.getColumnLabel(i));
        }
        while (rs.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 1; i <= columnSize; i++) {
                map.put(metaData.getColumnLabel(i), rs.getString(i));
            }
            result.get(DATA).add(map);
        }
        return result;
    }

    /**
     * 处理结果集
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Map parseResultSetToArray(ResultSet rs) throws SQLException {
        Map<String, List> result = new HashMap<>();
        result.put(COLUMN, new ArrayList<String>());
        result.put(DATA, new ArrayList<List>());

        ResultSetMetaData metaData = rs.getMetaData();
        int columnSize = metaData.getColumnCount();
        for (int i = 1; i <= columnSize; i++) {
            result.get(COLUMN).add(metaData.getColumnName(i));
        }
        while (rs.next()) {
            List<String> list = new ArrayList<>();
            for (int i = 1; i <= columnSize; i++) {
                list.add(rs.getString(i));
            }
            result.get(DATA).add(list);
        }
        return result;
    }

    /**
     * 获取freemark 模板
     *
     * @param templateName
     * @param param
     * @return
     */
    private String getFreeMarkTemplate(String templateName, Map<String, Object> param) {
        Configuration cfg = new Configuration();
        try {
            cfg.setClassForTemplateLoading(this.getClass(), "/freemark");
//            cfg.setDirectoryForTemplateLoading(new File("classpath:freemark"));
            Template template = cfg.getTemplate(templateName, "utf-8");

            StringWriter writer = new StringWriter();
            template.process(param, writer);
            return writer.toString();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将 siteIds 转化为 1,2,3
     *
     * @param
     * @return
     */
    private String getSiteIds(List list) {
        StringBuffer sb = new StringBuffer();
        list.stream().forEach(e -> {
            sb.append(e).append(",");
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
