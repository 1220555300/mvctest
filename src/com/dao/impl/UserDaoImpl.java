package com.dao.impl;

import com.dao.UserDao;
import com.vo.User;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao
{
    @Resource
    private JdbcTemplate jdbcTemplate;


    public User getUserByUserName(String userName){
        String sql = "select username,password from users where username = ?";
        List<User> list = jdbcTemplate.query(sql ,new String[]{userName},new RowMapper<User>(){

            public User mapRow(ResultSet resultSet,int i) throws SQLException{
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }
}
