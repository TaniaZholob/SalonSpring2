package com.tania.zholob.demo.model.services;

import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.entity.Time_slots;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.services.util.CreateList;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public interface MasterService {
    Iterable<Masters> getMasters(String sort);
    List<Orders> getAllRecords(Users user);
    Masters getMasterByUser(Users user);
    List<Time_slots> getAllTimeSlotsByMaster(Masters masters);
    CreateList getSchedule(Orders orders);
    Masters getMasterById(Long id);
    boolean save(Orders orders, Orders order);
}
