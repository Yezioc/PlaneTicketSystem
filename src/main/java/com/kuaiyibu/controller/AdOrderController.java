package com.kuaiyibu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/admin/orders")
public class AdOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{oid}")
    public Order getByOid(@PathVariable Integer oid){
        return orderService.getByOid(oid);
    }

    @GetMapping
    public List<Order> getByCondition(HttpServletRequest request) throws IOException {
        String json = "{";
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            if("ticket".equals(paraName)){
                json += "\""+paraName+"\""+": "+request.getParameter(paraName)+",";
            }else {

                json += "\""+paraName+"\""+": \""+request.getParameter(paraName)+"\",";
            }
        }
        json = json.substring(0,json.length()-1);
        json += "}";
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(json,Order.class);
        System.out.println(order);
        return orderService.getByCondition(order);
    }

    @GetMapping("/stated")
    public List<Order> getByUidAndStated(HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.getByUidAndStated(user.getUid());
    }

    @GetMapping("/ad")
    public List<Order> getAll(){
        return orderService.getAll();
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

    @PutMapping("/{oid}/{payState}/{state}")
    public Boolean adUpState(@PathVariable Integer oid,@PathVariable String payState,@PathVariable String state){
        return orderService.upDateByOidAndState(oid,payState,state);
    }

    @DeleteMapping("/{oid}")
    public Boolean deletedByUidAndOid(@PathVariable Integer oid,HttpSession session){
        User user = (User) session.getAttribute("user");
        return orderService.deletedByUidAndOid(user.getUid(),oid);
    }

}
