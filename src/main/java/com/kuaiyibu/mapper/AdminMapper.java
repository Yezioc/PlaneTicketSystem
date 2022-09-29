package com.kuaiyibu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaiyibu.pojo.Admin;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from tb_admin where aname=#{aname} and password=#{password}")
    Admin checkLogin(Admin admin);

}
