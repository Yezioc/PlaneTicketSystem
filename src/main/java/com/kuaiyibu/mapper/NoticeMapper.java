package com.kuaiyibu.mapper;

import com.kuaiyibu.pojo.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NoticeMapper {

    @Select("select * from tb_notice")
    List<Notice> selectAll();

    @Select("select * from tb_notice where nid=#{nid}")
    Notice getById(Integer nid);

    List<Notice> dySelect(Notice notice);

    @Insert("insert into tb_notice(title,content,time) values(#{title},#{content},#{time})")
    Integer addNotice(Notice notice);

    @Update("update tb_notice set title=#{title},content=#{content},time=#{time} where nid=#{nid}")
    Integer updateNotice(Notice notice);

    @Delete("delete from tb_notice where nid=#{nid}")
    Integer deleteNotice(Integer nid);

}

