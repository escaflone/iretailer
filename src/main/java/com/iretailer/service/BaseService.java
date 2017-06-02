package com.iretailer.service;

import com.iretailer.dto.DataQueryParam;
import com.iretailer.util.Constant;
import com.iretailer.util.MapBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clat on 2017/5/8.
 */
@Service
public class BaseService {

    @Autowired
    BasicDataSource dbcp;

    public Map query(DataQueryParam dqp) {
        Map<String, Object> result = new HashMap<>();
//        if (dqp.getDataFields().size() == 1) {
            handler(dqp);
//        }
        return result;
    }

    private Map handler(DataQueryParam dqp) {
        Map<String, String> config = Constant.dataFielsMap.get(dqp.getDataFields().get(0));
        String table = config.get(Constant.TABLE);
        String column = config.get(Constant.COLUMN);
        String st = Constant.timeFormat(dqp.getStartTime());
        String ed = Constant.timeFormat(dqp.getEndTime());
        String groupBy = Constant.groupByMap.get(dqp.getGroupBy().getPeriod());

        String[] columnsWithAs = new String[]{wrapSumWithAs(column), groupBy};
        String sortBy = parseSortBy(dqp.getSortBy(), Arrays.asList(new String[]{column}));

        Map<String,Object> params= new HashMap<>();
        params.put("column", MapBuilder.build(new HashMap<String,String>())
                .map("count_in","")
                .get());
        params.put("st",st);
        params.put("ed",ed);
        params.put("id",getSiteIds(dqp));

        String sql = getFreeMarkTemplate("inout.ftl",params);
        System.out.println(sql);
        query(sql);
        return null;
    }

    private String parseSortBy(Map<String, Integer> sortByMap, List<String> columns) {
        StringBuilder sb = new StringBuilder("");
        for (String key : sortByMap.keySet()) {
            String col = Constant.dataFielsMap.get(key) != null ? Constant.dataFielsMap.get(key).get(Constant.COLUMN) : key;
            if (columns.contains(col)) {
                sb.append(wrapSum(col));
            } else {
                sb.append(col);
            }
            if (sortByMap.get(key) != 1) {
                sb.append(" desc ");
            }
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            return " order by " + sb.toString();
        }
        return null;
    }

    private void query(String sql) {
        try {
            Connection conn = dbcp.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Map map = parseResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String wrapSum(String col) {
        return "sum(" + col + ")";
    }

    private String wrapSumWithAs(String col) {
        return "sum(" + col + ") as " + col;
    }
    /**
     * 处理结果集
     * @param rs
     * @return
     * @throws SQLException
     */
    private Map parseResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnSize = metaData.getColumnCount();
        for (int i = 1; i <= columnSize; i++) {
            System.out.print(metaData.getColumnName(i));
            System.out.print("\t\t");
        }
        while (rs.next()) {
            for (int i = 1; i <= columnSize; i++) {
                System.out.print(rs.getString(i));
                System.out.print("\t\t");
            }
            System.out.println();
        }
        return null;
    }

    /**
     * 获取freemark 模板
     * @param templateName
     * @param param
     * @return
     */
    private String getFreeMarkTemplate(String templateName, Map<String, Object> param){
        Configuration cfg = new Configuration();
        try {
            cfg.setClassForTemplateLoading(this.getClass(),"/freemark");
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
     * @param dqp
     * @return
     */
    private String getSiteIds(DataQueryParam dqp){
        StringBuffer sb = new StringBuffer();
        dqp.getSiteIdList().stream().forEach(e ->{
            sb.append(e).append(",");
        });
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
