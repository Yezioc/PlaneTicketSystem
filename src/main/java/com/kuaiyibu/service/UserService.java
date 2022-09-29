package com.kuaiyibu.service;

import com.kuaiyibu.pojo.User;

import java.util.List;

public interface UserService {

    User checkLogin(User user);

    List<User> selectAll();

    User selectByUid(Integer uid);

    Integer addUser(User user);

    Integer deleteUser(Integer uid);

    Integer updateUser(User user);

    List<User> dySelect(User user);

    Integer checkuserName(String userName);
}
