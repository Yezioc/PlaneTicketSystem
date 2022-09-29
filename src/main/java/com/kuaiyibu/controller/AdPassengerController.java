package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Passenger;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/admin/passengers")
public class AdPassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("/selectAll")
    public List<Passenger> selectAll(){
        return passengerService.selectAll();
    }
    /*模糊查询*/
    @PostMapping("/mohu")
    public List<Passenger> MohuchaxPassenger(@RequestBody Passenger passenger){
        return passengerService.dySelect1(passenger);
    }

    /*删除信息*/
    @DeleteMapping("/{pid}")
    public Boolean DeletePassenger(@PathVariable int pid){
        System.out.println(pid);
        if(passengerService.deletePassengertrue(pid)>0){
            return true;
        }

        return false;
    }


    /*修改信息*/
    @PutMapping
    public List<Passenger> updatePassenger(@RequestBody Passenger passenger){
        passengerService.changePassenger(passenger);
        return passengerService.selectByUid(passenger.getUid());
    }
}
