package com.dao;

import com.vo.User;

public interface UserDao {
    public User getUserByUserName(String userName);
}
