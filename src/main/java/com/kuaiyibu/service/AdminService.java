package com.kuaiyibu.service;

import com.kuaiyibu.pojo.Admin;

public interface AdminService {
    Admin checkLogin(Admin admin);

    Admin getAtitleName(Integer aid);
}
