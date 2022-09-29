package com.kuaiyibu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuaiyibu.pojo.Ticket;
import com.kuaiyibu.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{tid}")
    public Ticket getById(@PathVariable Integer tid) {
        return ticketService.getById(tid);
    }

    @GetMapping("/{origin}/{end}/{date}")
    public List<Ticket> getByCondition(@PathVariable String origin, @PathVariable String end, @PathVariable String date) {
        return ticketService.getByCondition(origin,end,date);
    }

    @GetMapping("/{origin}/{end}/{date}/{prop}/{sort}")
    public List<Ticket> getByConditionAndOrder(@PathVariable String origin, @PathVariable String end, @PathVariable String date,@PathVariable String prop, @PathVariable String sort) {
        return ticketService.getByConditionAndOrder(origin,end,date,prop,sort);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public IPage<Ticket> getPageByCondition(@PathVariable int currentPage, @PathVariable int pageSize, Ticket ticket) {
        IPage<Ticket> page = ticketService.getPageByCondition(currentPage,pageSize,ticket);
        if(currentPage > page.getPages()){
            page = ticketService.getPageByCondition((int) page.getPages(), pageSize,ticket);
        }
        return page;
    }

    @GetMapping("/city/{col}")
    public List<Ticket> getCity(@PathVariable String col) {
        return ticketService.getCity(col);
    }

    /*@PutMapping
    public boolean update(@RequestBody Ticket ticket) {
        return ticketService.updateById(ticket);
    }

    @PostMapping
    public boolean add(@RequestBody Ticket ticket) {
        return ticketService.save(ticket);
    }

    @DeleteMapping("/{tid}")
    public boolean deleteById(@PathVariable Integer tid) {
        return ticketService.removeById(tid);
    }

    @DeleteMapping
    public boolean deleteByIds(@RequestBody List<Integer> tids) {
        if(tids.isEmpty()){
            return false;
        }
        return ticketService.removeByIds(tids);
    }*/

}
