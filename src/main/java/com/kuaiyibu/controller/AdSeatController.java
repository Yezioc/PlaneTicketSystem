package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Seat;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.SeatService;
import com.kuaiyibu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/admin/seats")
public class AdSeatController {

    @Autowired
    private SeatService seatService;


    @PostMapping ("/{tid}")
    public boolean addSeat(@PathVariable Integer tid){
        boolean result= seatService.addSeat(tid);
        return result;
    }


    @GetMapping("/get/{tid}")
    public Integer findSeatByTid(@PathVariable Integer tid){

        return seatService.findSeatByTid(tid);
    }

    @GetMapping("/ckseat/{tid}")
    public Boolean findSeatByTidck(@PathVariable Integer tid){

        return seatService.findSeatByTidck(tid);
    }


    @GetMapping("/{sid}")
    public Seat findBySid(@PathVariable Integer sid){

        return seatService.findBySid(sid);
    }

    @GetMapping("/getseats/{tid}")
    public List<Seat> getSeatsByTid(@PathVariable Integer tid){
        return seatService.getSeatsByTid(tid);
    }

    @PutMapping("/{sid}")
    public boolean upDateSeatState(@PathVariable Integer sid){
       boolean upState= seatService.upDateSeatState(sid,0);
       return upState;
    }

    @PutMapping("/{sid}/{seatState}")
    public boolean upDateSeatState(@PathVariable Integer sid,@PathVariable Integer seatState){
        boolean upState= seatService.upDateSeatState(sid,seatState);
        return upState;
    }
    @GetMapping("/getSeatTid/{tid}")
    public Integer findSeatByTid1(@PathVariable Integer tid) {

        Integer returnSid= seatService.findSeatByTid1(tid);
        return returnSid;}
}
