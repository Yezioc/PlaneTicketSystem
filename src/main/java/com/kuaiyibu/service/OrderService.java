package com.kuaiyibu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kuaiyibu.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    Order getByOid(Integer oid);

    List<Order> getByUidAndStated(Integer uid);

    List<Order> getAll();

    List<Order> getByCondition(Order order);

    List<Order> getByUidAndPayState(Integer uid,String payState);

    List<Order> getByUidAndState(Integer uid,String state);

    Integer saveOrder(Integer pid, Integer tid);

    Boolean cancelOrder(Integer oid);

    Boolean payOrder(Integer oid);

    Boolean upDateByOidAndState(Integer oid,String payState,String state);

    Boolean deletedByUidAndOid(Integer uid,Integer oid);

    Order getByOidAndUid(Integer oid, Integer uid);

}
