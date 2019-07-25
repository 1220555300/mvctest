package com.services;

import com.dao.JdbcTempletes;
import com.entity.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class listService extends BaseService {
    @Autowired
    private JdbcTempletes jdbcTemplete;

    public PageData findPlanBy(String planName, String planStatus, int page, int pageSize, boolean onlyInTime, String creatorId){
        String condition0 = " and (p.only_visible_to_creator is null or p.CREATORID = ?)";
        String condition1 = " and p.title like '%'||?||'%'";
        String condition2 = " and p.status like '%'||?||'%'";
        String condition3 = " and sysdate > p.beg_time and sysdate < (p.end_time + 1)";
        String sql = "select p.* from survey_plan p inner join survey_model m on p.ref_model_id = m.id";
        List<Object> argsList = new ArrayList<Object>();
        if(creatorId != null){
            sql = sql + condition0;
            argsList.add(creatorId);
        }
        if(onlyInTime){
            sql = sql + condition3;
        }
        if(planName != null && planName.trim().length()>0){
            sql = sql + condition1;
        }
        if(planStatus != null ){
            sql = sql + condition2;
            argsList.add(planStatus);
        }
        sql = sql + " order by p.end_time desc";
        return jdbcTemplete.queryForOraclePage(sql, argsList.toArray(),page,pageSize);
    }

    public int updatePlan (String id, String title, String status, Date begTime, Date endTime){
        String sql = "update survey_plan set title=?,status=?,beg_time=?,end_time=? where id= ? ";
        return jdbcTemplete.update(sql,new Object[]{title,status,begTime,endTime,id});
    }

    public boolean isPlanCanModify(String id){
        String sql = "select count(1) from survey_answer where plan_id = ?";
        int x = jdbcTemplete.queryForObject(sql,new Object[]{id},Integer.class );
        return  x<1;
    }









}
