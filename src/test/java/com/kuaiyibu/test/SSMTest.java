package com.kuaiyibu.test;

import com.kuaiyibu.mapper.OrderMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.Ticket;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.OrderService;
import com.kuaiyibu.service.TicketService;
import com.kuaiyibu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SSMTest {

    @Autowired
    private OrderService service;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private TicketService ticketService;

    @Test
    public void test1() {
//        Ticket ticket = new Ticket();
//        ticket.setStartTime("45:12");
//        ticket.setEndTime("45:13");
//        ticket.setOrigin("ss");
//        ticket.setEnd("dd");
//        System.out.println(ticketService.add(ticket));
//        int s = Integer.parseInt("1220220220512101233");
//        Integer integer = service.saveOrder(100010002, 10026);
//        System.out.println(integer);
    }

}
