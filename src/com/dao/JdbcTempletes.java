package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.entity.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JdbcTempletes  extends JdbcTemplate {

    public void add(String tabName, Map<String, Object> dataMap, List<String> notInsertField){
        StringBuilder fieldsb = new StringBuilder();
        StringBuilder possb  = new StringBuilder();
        List<Object> params  = new ArrayList<Object>();
        for (String field : dataMap.keySet()){
            if(notInsertField  == null || !notInsertField.contains(field)){
                fieldsb.append(field).append(",");
                possb.append("?,");
                params.add(dataMap.get(field));
            }

        }
        String sql = "insert into "+tabName+"( "+fieldsb.substring(0,fieldsb.length()-1)+") values("+possb.substring(0,possb.length()-1)+")";
        update(sql, params.toArray());
    }

    public PageData queryForOraclePage(String sql,Object[] args, int page , int size){

        if (page < 1){
            page = 1;
        }
        if (size < 1){
            size = 20;
        }
        PageData pageData = new PageData();
        pageData.setCurrentPage(page);
        pageData.setCurrentPageSize(size);
        int start = (page-1) * size+1;
        int end = start + size;
        String sqlCount = "select count(1) from ("+sql+")  tmp";
        String sqlPage = "select * from (select a.*,rownum rn from ("+sql+") a) where rn between "+ start +" and " + (end -1);
        int count = queryForObject(sqlCount,args,Integer.class);
        pageData.setTotalCount(count);

        if(count > 0){
            List<?> datas = queryForList(sqlPage,args);
           System.out.println(datas.toString());
            pageData.setDatas(datas);
        }
        return pageData;
    }

    public void delete(String tableName,String id){
        String sql = "delete from "+tableName+" where id = ?";
        update (sql,new Object[]{id});
    }




}
