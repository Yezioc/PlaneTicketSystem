package com.kuaiyibu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuaiyibu.mapper.OrderMapper;
import com.kuaiyibu.mapper.PassengerMapper;
import com.kuaiyibu.mapper.SeatMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.Passenger;
import com.kuaiyibu.pojo.Seat;
import com.kuaiyibu.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author HASEE
 */
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public Order getByOid(Integer oid) {
        Order order = orderMapper.selectByOid(oid);
        if(order == null){
            order = orderMapper.selectCelByOid(oid);
        }
        return order;
    }

    @Override
    public List<Order> getByUidAndStated(Integer uid) {
        List<Order> orders = orderMapper.selectByUidAndStated(uid);
        orders.addAll(orderMapper.selectByUidAndState(uid, "已取消"));
        return orders;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderMapper.selectAll();
        orders.addAll(orderMapper.selectAllCel());
        return orders;
    }

    @Override
    public List<Order> getByCondition(Order order) {
        List<Order> orders = orderMapper.selectByCondition(order);
        orders.addAll(orderMapper.selectByConditionCel(order));
        return orders;
    }

    @Override
    public List<Order> getByUidAndPayState(Integer uid,String payState) {
        return orderMapper.selectByUidAndPayState(uid,payState);
    }

    @Override
    public List<Order> getByUidAndState(Integer uid, String state) {
        return orderMapper.selectByUidAndState(uid, state);
    }

    @Override
    public Integer saveOrder(Integer pid, Integer tid) {
        //检查是否重复购买
        if(orderMapper.ckOrder(pid, tid) != null){
            return 1;
        }else{
            //检查余座并获取sid
            QueryWrapper<Seat> seatWrapper = new QueryWrapper<>();
            seatWrapper.lambda().eq(Seat::getTid,tid).eq(Seat::getSeatState,0);
            List<Seat> seats = seatMapper.selectList(seatWrapper);
            if(seats.size() > 0){
                //修改座位状态
                UpdateWrapper<Seat> seatUpdateWrapper = new UpdateWrapper<>();
                seatUpdateWrapper.lambda().set(Seat::getSeatState,1).eq(Seat::getSid,seats.get(0).getSid());
                seatMapper.update(null,seatUpdateWrapper);
                //获取uid
                Passenger passenger = passengerMapper.selectOne(new QueryWrapper<Passenger>().lambda().eq(Passenger::getPid, pid));

                Date date = new Date();
                // 生成订单编号onum
                String onum = new SimpleDateFormat("12202yyyyMMddhhmmssS").format(date);
                //获取现在时间
                String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
                //生成订单
                orderMapper.insertOrder(onum, passenger.getUid(), pid, tid, seats.get(0).getSid(), time);
                //获得oid并返回
                return orderMapper.ckOrder(pid, tid).getOid();
            }else{
                return 0;
            }
        }
    }

    @Override
    public Boolean cancelOrder(Integer oid) {
        boolean exflag = true;
        //查询座位sid
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.lambda().select(Order::getOsid).eq(Order::getOid,oid);
        Order order = orderMapper.selectOne(orderQueryWrapper);
        //修改座位状态
        UpdateWrapper<Seat> seatUpdateWrapper = new UpdateWrapper<>();
        seatUpdateWrapper.lambda().set(Seat::getSeatState,0).eq(Seat::getSid,order.getOsid());
        int sup = seatMapper.update(null, seatUpdateWrapper);
        if(sup == 0){
            exflag = false;
        }
        //改变状态为已取消 和座位为null
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(Order::getState,"已取消").set(Order::getOsid,null).eq(Order::getOid,oid);
        int oup = orderMapper.update(null, updateWrapper);
        if(oup == 0){
            exflag = false;
        }
        return exflag;
    }

    @Override
    public Boolean payOrder(Integer oid) {
        //改变状态为已购买 和 支付状态为已支付
        UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(Order::getState,"已购买").set(Order::getPayState,"已支付").eq(Order::getOid,oid);
        return orderMapper.update(null, updateWrapper) > 0;
    }

    @Override
    public Boolean upDateByOidAndState(Integer oid, String payState, String state) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(Order::getPayState,payState).set(Order::getState,state).eq(Order::getOid,oid);
        return orderMapper.update(null,wrapper) > 0;
    }

    @Override
    public Boolean deletedByUidAndOid(Integer uid, Integer oid) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().select(Order::getOuid,Order::getOid).eq(Order::getOuid,uid).eq(Order::getOid,oid);
        Order order = orderMapper.selectOne(queryWrapper);
        if(order != null){
           UpdateWrapper<Order> updateWrapper = new UpdateWrapper<>();
           updateWrapper.lambda().set(Order::getDeleted,1).eq(Order::getOid,oid);
           return orderMapper.update(null, updateWrapper) > 0;
        }
        return false;
    }

    @Override
    public Order getByOidAndUid(Integer oid, Integer uid) {
        return orderMapper.selectByOidAndUid(oid, uid);
    }
}
