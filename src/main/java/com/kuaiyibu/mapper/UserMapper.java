package com.kuaiyibu.mapper;

import com.kuaiyibu.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {


    @Select("select * from tb_user where user_name = #{userName} and password = #{password}")
    @ResultMap("userMap")
    User checkLogin(User user);

    @Select("select * from tb_user")
    @ResultMap("userMap")
    List<User> selectAll();

    @Select("select * from tb_user where uid=#{uid}")
    @ResultMap("userMap")
    User selectByUid(Integer uid);

    @Insert("Insert into tb_user values(null,#{userName},#{titleName},#{password},#{sex},#{phone},#{birthday},#{email})")
    Integer addUser(User user);

    @Delete("delete from tb_user where uid=#{uid}")
    Integer deleteUser(Integer uid);

    @Update("update tb_user set title_Name=#{titleName},password=#{password},sex=#{sex},phone=#{phone},birthday=#{birthday},email=#{email},user_name=#{userName} where uid=#{uid}")
    Integer updateUser(User user);

    List<User> dySelect(User user);

    @Select("select * from tb_user where user_name=#{userName}")
    Integer checkuserName(String userName);

}
