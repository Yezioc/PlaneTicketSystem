package com.kuaiyibu.mapper;

import com.kuaiyibu.pojo.Advice;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface AdviceMapper {

    @Select("select * from tb_advice")
    @ResultMap("adviceMap")
    List<Advice> selectAll();

    @Select("select * from tb_advice where aid=#{aid}")
    @ResultMap("adviceMap")
    Advice getAId(Integer aid);

    List<Advice> dySelect(Advice advice);

    @Insert("Insert into tb_advice(advice_type,advice_content,phone,time) values(#{adviceType},#{adviceContent},#{phone},#{time})")
    Integer addAdvice(Advice advice);

    @Delete("delete from tb_advice where aid=#{aid}")
    Integer deleteAdvice(Integer aid);

}
