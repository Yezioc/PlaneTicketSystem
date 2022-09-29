package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.AdminMapper;
import com.kuaiyibu.pojo.Admin;
import com.kuaiyibu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin checkLogin(Admin admin) {
        return adminMapper.checkLogin(admin);
    }

    @Override
    public Admin getAtitleName(Integer aid) {
        return adminMapper.selectById(aid);
    }
}
