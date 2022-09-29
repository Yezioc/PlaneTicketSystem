package com.kuaiyibu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuaiyibu.pojo.Order;
import com.kuaiyibu.pojo.Seat;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SeatMapper extends BaseMapper<Seat> {

    @Insert("Insert into tb_seat(tid,passage_seat) values(#{tid},#{passageSeat})")
    Boolean addSeat(@Param("tid") Integer tid, @Param("passageSeat") String passageSeat);

    @Select("select count(*) from tb_seat where tid=#{tid} and seat_state = 0")
    Integer findSeatByTid(Integer tid);

    @Update("update tb_seat set seat_state=#{seatState} Where sid=#{sid}")
    Boolean upDateSeatState(@Param("sid") Integer sid,@Param("seatState") Integer seatState);

    @Select("select * from tb_seat where tid=#{tid} and seat_state=0 orderBy sid")
    @ResultMap("seatMap")
    List<Seat> findSeatByTid1(Integer tid);

    @Select("select * from tb_seat where sid=#{sid}")
    @ResultMap("seatMap")
    Seat findBySid(Integer sid);

    @Select("select * from tb_seat where tid=#{tid}")
    @ResultMap("seatMap")
    List<Seat> selectSeatsByTid(Integer tid);

}
