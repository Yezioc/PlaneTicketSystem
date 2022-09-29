package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.UserMapper;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkLogin(User user) {
        User user1 = userMapper.checkLogin(user);
        return user1;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectByUid(Integer uid) {
        return userMapper.selectByUid(uid);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer deleteUser(Integer uid) {
        return userMapper.deleteUser(uid);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<User> dySelect(User user) {
        return userMapper.dySelect(user);
    }

    @Override
    public Integer checkuserName(String userName) {
        return userMapper.checkuserName(userName);
    }


}
