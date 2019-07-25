package com.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.JdbcTempletes;
import java.util.List;
import java.util.Map;

public class BaseService {
    @Autowired
    private JdbcTempletes jdbcTemplate;

    public void add(String tableName, Map<String, Object> dataMap, List<String> notInserField){
        jdbcTemplate.add(tableName,dataMap,notInserField);
    }

    public void  delete(String tableName,String id){
        jdbcTemplate.delete(tableName,id);
    }
}
