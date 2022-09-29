package com.kuaiyibu.service;

import com.kuaiyibu.pojo.Passenger;
import com.kuaiyibu.pojo.User;

import java.util.List;

public interface PassengerService {
    List<Passenger> selectByUid(int uid);
    Passenger selectByUidAndPid(int uid,int pid);


    List<Passenger> selectAll();


    Integer addPassenger(Passenger passenger);

    Integer deletePassenger(Integer pid);
    Integer deletePassengertrue(Integer pid);

    Integer changePassenger(Passenger passenger);

    List<Passenger> dySelect(Passenger passenger);
    List<Passenger> dySelect1(Passenger passenger);

    Integer checkpassenger(Passenger passenger);

    Passenger checkAllpassenger(Passenger passenger);

    Boolean changedeleted(Passenger passenger);
}
