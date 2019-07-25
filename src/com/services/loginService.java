package com.services;

import com.dao.JdbcTempletes;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class loginService {

    @Resource
    private JdbcTempletes jdbcTemplete;



    /**
     * 根据用户登陆名获取用户信息
     * @param loginName
     * @return
     */
    public Map<String, Object> getMember(String loginName){
        Map<String, Object> result;
        try {
            String sql = "select * from member where login_name = ? ";
            result = jdbcTemplete.queryForMap(sql,new Object[]{loginName});
            return result;
        } catch (DataAccessException e) {
            return null;
        }
    }

}
