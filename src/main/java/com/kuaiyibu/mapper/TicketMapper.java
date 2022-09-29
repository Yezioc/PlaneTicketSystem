package com.kuaiyibu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaiyibu.pojo.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TicketMapper extends BaseMapper<Ticket> {

}
