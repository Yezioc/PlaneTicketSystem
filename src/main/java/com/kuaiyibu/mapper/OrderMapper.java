package com.kuaiyibu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.Ticket;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_seat s,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and o.osid=s.sid and oid=#{oid}")
    @ResultMap("orderMap")
    Order selectByOid(Integer oid);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_seat s,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and o.osid=s.sid and oid=#{oid} and ouid=#{uid}")
    @ResultMap("orderMap")
    Order selectByOidAndUid(@Param("oid") Integer oid,@Param("uid") Integer uid);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and oid=#{oid} and state='已取消' ")
    @ResultMap("orderMap")
    Order selectCelByOid(Integer oid);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_seat s,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and o.osid=s.sid " +
            "order by time DESC")
    @ResultMap("orderMap")
    List<Order> selectAll();

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and state='已取消' " +
            "order by time DESC")
    @ResultMap("orderMap")
    List<Order> selectAllCel();

    @Insert("insert into tb_order(onum,ouid,opid,otid,osid,time) value(#{onum},#{uid},#{pid},#{tid},#{sid},#{time})")
    Integer insertOrder(@Param("onum") String onum, @Param("uid") Integer uid, @Param("pid") Integer pid, @Param("tid") Integer tid, @Param("sid") Integer sid, @Param("time") String time);

    @Select("select * from tb_order where opid=#{pid} and otid=#{tid} and state!='已取消'")
    Order ckOrder(@Param("pid") Integer pid, @Param("tid") Integer tid);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_seat s,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and o.osid=s.sid and ouid=#{uid} and pay_state!='未支付' and deleted=0 " +
            "order by time DESC")
    @ResultMap("orderMap")
    List<Order> selectByUidAndStated(Integer uid);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_seat s,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and o.osid=s.sid and ouid=#{uid} and pay_state=#{payState} and deleted=0 " +
            "order by time DESC")
    @ResultMap("orderMap")
    List<Order> selectByUidAndPayState(@Param("uid") Integer uid,@Param("payState")String payState);

    @Select("select *" +
            "from tb_passenger p,tb_ticket t,tb_order o " +
            "where o.opid=p.pid and o.otid=t.tid and ouid=#{uid} and state=#{state} and deleted=0 " +
            "order by time DESC")
    @ResultMap("orderMap")
    List<Order> selectByUidAndState(@Param("uid") Integer uid,@Param("state")String state);

    List<Order> selectByCondition(Order order);

    List<Order> selectByConditionCel(Order order);

}
