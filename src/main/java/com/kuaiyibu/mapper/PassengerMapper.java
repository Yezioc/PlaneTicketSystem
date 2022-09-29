package com.kuaiyibu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.Passenger;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PassengerMapper extends BaseMapper<Passenger> {

    /**
     * 查询该用户所有乘车人信息*/
    @Select("select * from tb_passenger where uid=#{uid} and pdeleted='0'")
    @ResultMap("passengerMap")
    List<Passenger> selectByUid(int uid);

    @Select("select * from tb_passenger where uid=#{uid} and pid=#{pid}")
    @ResultMap("passengerMap")
    Passenger selectByUidAndPid(@Param("uid") int uid,@Param("pid") int pid);

    @Select("select * from tb_passenger")
    @ResultMap("passengerMap")
    List<Passenger> selectAll();

    /**
     * 增加*/
    @Insert("insert into tb_passenger values(null,#{psName},#{cardType},#{idCard},#{uid},'0')")
    Integer addPassenger(Passenger passenger);

    /**
     * 删除*/
    @Update("update tb_passenger set pdeleted='1' where pid=#{pid}")
    Integer deletePassenger(Integer pid);

    @Delete("delete from tb_passenger where pid=#{pid}")
    Integer deletePassengertrue(Integer pid);

    /**
     * 更新
     * */
    @Update("update tb_passenger set p_name=#{psName},card_type=#{cardType},id_card=#{idCard} where pid=#{pid}")
    Integer changePassenger(Passenger passenger);

    /**
     * 动态查询*/
    List<Passenger> dySelect(Passenger passenger);
    List<Passenger> dySelect1(Passenger passenger);

    /**
     * 检查*/
    @Select("select * from tb_passenger where card_type=#{cardType} and id_card=#{idCard} ORDER BY p_name desc LIMIT 1")
    @ResultMap("passengerMap")
    Passenger checkAllpassenger(Passenger passenger);

    @Select("select * from tb_passenger where card_type=#{cardType} and id_card=#{idCard} and uid=#{uid}")
    Integer checkpassenger(Passenger passenger);

    @Update("update tb_passenger set pdeleted='0' where pid=#{pid}")
    Boolean changedeleted(Passenger passenger);
}
