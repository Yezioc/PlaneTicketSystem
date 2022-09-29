package com.kuaiyibu.service.impl;

import com.kuaiyibu.mapper.PassengerMapper;
import com.kuaiyibu.pojo.Passenger;
import com.kuaiyibu.pojo.User;
import com.kuaiyibu.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerMapper passengerMapper;

    @Override
    public List<Passenger> selectByUid(int uid) {
        return passengerMapper.selectByUid(uid);
    }

    @Override
    public Passenger selectByUidAndPid(int uid, int pid) {

        return passengerMapper.selectByUidAndPid(uid,pid);
    }

    @Override
    public List<Passenger> selectAll() {
        return passengerMapper.selectAll();
    }

    @Override
    public Integer addPassenger(Passenger passenger) {
        return passengerMapper.addPassenger(passenger);
    }

    @Override
    public Integer deletePassenger(Integer pid) {
        return passengerMapper.deletePassenger(pid);
    }

    @Override
    public Integer deletePassengertrue(Integer pid) {
        return passengerMapper.deletePassengertrue(pid);
    }

    @Override
    public Integer changePassenger(Passenger passenger) {
        return passengerMapper.changePassenger(passenger);
    }

    @Override
    public List<Passenger> dySelect(Passenger passenger) {
        return passengerMapper.dySelect(passenger);
    }
    @Override
    public List<Passenger> dySelect1(Passenger passenger) {
        return passengerMapper.dySelect1(passenger);
    }

    @Override
    public Integer checkpassenger(Passenger passenger) {
        return passengerMapper.checkpassenger(passenger);
    }
    @Override
    public Passenger checkAllpassenger(Passenger passenger) {
        return passengerMapper.checkAllpassenger(passenger);
    }

    @Override
    public Boolean changedeleted(Passenger passenger) {
        return passengerMapper.changedeleted(passenger);
    }
}
