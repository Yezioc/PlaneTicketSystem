package com.kuaiyibu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuaiyibu.pojo.Ticket;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TicketService extends IService<Ticket> {

    List<Ticket> getByCondition(String origin, String end, String startDate);

    List<Ticket> getByConditionAndOrder( String origin, String end, String date, String prop, String sort);

    List<Ticket> getCity(String col);

    IPage<Ticket> getPageByCondition(int currentPage, int pageSize, Ticket ticket);

    Boolean addSeat(Integer tid);

    Integer add(Ticket ticket);

    Boolean update(Ticket ticket);

}
