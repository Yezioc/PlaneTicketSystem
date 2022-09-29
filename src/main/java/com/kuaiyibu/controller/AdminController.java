package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Admin;
import com.kuaiyibu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public Boolean checkLogin(Admin admin, HttpSession session){
        System.out.println("-------");
        admin =adminService.checkLogin(admin);
        if(admin!=null){
            session.setAttribute("admin",admin);
            return true;
        }
        return false;
    }



    @GetMapping("/check")
    public Admin getAdtitleName(HttpSession session){
        return (Admin) session.getAttribute("admin");
    }
}
