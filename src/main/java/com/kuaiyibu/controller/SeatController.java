package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Seat;
import com.kuaiyibu.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HASEE
 */
@RestController
@RequestMapping("/user/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;


    @GetMapping("/ckseat/{tid}")
    public Boolean findSeatByTidck(@PathVariable Integer tid){

        return seatService.findSeatByTidck(tid);
    }

}
