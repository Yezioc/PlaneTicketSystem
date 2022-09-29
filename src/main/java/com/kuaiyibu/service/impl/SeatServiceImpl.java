package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.SeatMapper;
import com.kuaiyibu.pojo.Seat;
import com.kuaiyibu.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatMapper seatMapper;

    @Override
    public Boolean addSeat(Integer tid) {
        try {
            for (int i = 1; i <= 50; i++) {
                if(i < 10){
                    seatMapper.addSeat(tid,"0"+i+"A");
                    seatMapper.addSeat(tid,"0"+i+"B");
                    seatMapper.addSeat(tid,"0"+i+"C");
                    seatMapper.addSeat(tid,"0"+i+"E");
                    seatMapper.addSeat(tid,"0"+i+"F");
                    seatMapper.addSeat(tid,"0"+i+"G");
                }else {
                    seatMapper.addSeat(tid,i+"A");
                    seatMapper.addSeat(tid,i+"B");
                    seatMapper.addSeat(tid,i+"C");
                    seatMapper.addSeat(tid,i+"E");
                    seatMapper.addSeat(tid,i+"F");
                    seatMapper.addSeat(tid,i+"G");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer findSeatByTid(Integer tid) {
        Integer count=seatMapper.findSeatByTid(tid);
        return count;//返回了剩余票数
    }

    @Override
    public Boolean findSeatByTidck(Integer tid) {
        return seatMapper.findSeatByTid(tid) > 0;
    }


    @Override
    public Boolean upDateSeatState(Integer sid, Integer seatState) {
        return  seatMapper.upDateSeatState(sid,seatState);
    }

    @Override
    public Integer findSeatByTid1(Integer tid) {
      List<Seat> seat= seatMapper.findSeatByTid1(tid);
        seat.get(0).setSeatState(1);
       return seat.get(0).getSid();
    }

    @Override
    public Seat findBySid(Integer sid){

        return  seatMapper.findBySid(sid);
    }
    @Override
    public List<Seat> getSeatsByTid(Integer tid) {
        return seatMapper.selectSeatsByTid(tid);
    }
}







