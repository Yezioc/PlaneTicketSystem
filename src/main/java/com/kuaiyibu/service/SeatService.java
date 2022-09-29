package com.kuaiyibu.service;

import com.kuaiyibu.pojo.Seat;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface SeatService {

    Boolean addSeat(Integer tid);
    Integer findSeatByTid(Integer tid);
    Boolean findSeatByTidck(Integer tid);
    Boolean upDateSeatState(Integer sid,Integer seatState);
    Integer findSeatByTid1(Integer tid);
    Seat findBySid(Integer sid);
    List<Seat> getSeatsByTid(Integer tid);
}
