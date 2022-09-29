package com.kuaiyibu.controller;

import com.kuaiyibu.pojo.Passenger;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user/passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;


    /*获取机票和用户乘车人信息*/
    @GetMapping("/seletByUid")
    public List<Passenger> selectAll(HttpSession session){
        User user= (User) session.getAttribute("user");
        List<Passenger> passengers = passengerService.selectByUid(user.getUid());
        System.out.println(passengers);
        return passengers;
    }

    @GetMapping("/{pid}")
    public Passenger selectByPid(@PathVariable Integer pid, HttpSession session){
        User user= (User) session.getAttribute("user");
        System.out.println(passengerService.selectByUid(user.getUid()));
        return passengerService.selectByUidAndPid(user.getUid(),pid);
    }

    /*添加信息*/
    @PostMapping
    public Boolean addPassenger(@RequestBody Passenger passenger, HttpSession session){
        User user= (User) session.getAttribute("user");
        passenger.setUid(user.getUid());

        Passenger passenger1=passengerService.checkAllpassenger(passenger);
        System.out.println(passenger+"*******"+passenger1);
        /*先判断全部用户下有没有该乘车人，无则加*/
        if(passenger1==null){
            passengerService.addPassenger(passenger);
            return true;
        }
       /* 若有则进行判断输入用户名是否相同，不同直接return false*/
        else if(passenger1.getPsName().equals(passenger.getPsName())){
            System.out.println(passengerService.changedeleted(passenger1));
            return true;
        }
       /* 输入信息相同则判断是否在该用户下有该乘车人，无则添加*/
        else if(passengerService.checkpassenger(passenger)==null){
            passengerService.addPassenger(passenger);
            return true;
        }
        return false;
    }

    /*删除信息*/
    @DeleteMapping("/{pid}")
    public Boolean DeletePassenger(@PathVariable int pid){
        System.out.println(pid);
        if(passengerService.deletePassenger(pid)>0){
            return true;
        }

        return false;
    }

    /*模糊查询*/
    @PostMapping("/mohu")
    public List<Passenger> MohuchaxPassenger(@RequestBody Passenger passenger,HttpSession session){
        User user= (User) session.getAttribute("user");
        passenger.setUid(user.getUid());
        System.out.println(passenger);
        return passengerService.dySelect(passenger);
    }

    /*修改信息*/
    @PutMapping
    public List<Passenger> updatePassenger(@RequestBody Passenger passenger){
        passengerService.changePassenger(passenger);
        return passengerService.selectByUid(passenger.getUid());
    }

}
