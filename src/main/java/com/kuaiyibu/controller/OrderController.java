package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/user/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{oid}")
    public Order getByOidAndUid(@PathVariable Integer oid,HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByOidAndUid(oid,user.getUid());
    }

    @GetMapping("/stated")
    public List<Order> getByUidAndStated(HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByUidAndStated(user.getUid());
    }

    @GetMapping("/noPay")
    public List<Order> getByUidAndNoPay(HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByUidAndPayState(user.getUid(),"未支付");
    }

    @GetMapping("/payed")
    public List<Order> getByUidAndPayed(HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByUidAndPayState(user.getUid(),"已支付");
    }

    @GetMapping("/cel")
    public List<Order> getByUidAndState(HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByUidAndState(user.getUid(),"已取消");
    }

    @PostMapping("/{pid}/{tid}")
    public Integer saveOrder(@PathVariable Integer pid, @PathVariable Integer tid){
        return orderService.saveOrder(pid,tid);
    }

    @PutMapping("/ex/{oid}")
    public Boolean cancelOrder(@PathVariable Integer oid){
        return orderService.cancelOrder(oid);
    }

    @PutMapping("/pay/{oid}")
    public Boolean payOrder(@PathVariable Integer oid){
        return orderService.payOrder(oid);
    }

    @DeleteMapping("/{oid}")
    public Boolean deletedByUidAndOid(@PathVariable Integer oid,HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.deletedByUidAndOid(user.getUid(),oid);
    }

}
