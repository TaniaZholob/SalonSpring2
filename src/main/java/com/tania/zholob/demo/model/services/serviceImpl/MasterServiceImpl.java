package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.entity.*;
import com.tania.zholob.demo.model.repos.MasterRepo;
import com.tania.zholob.demo.model.repos.OrderRepo;
import com.tania.zholob.demo.model.repos.TimeSlotRepo;
import com.tania.zholob.demo.model.services.MasterService;
import com.tania.zholob.demo.model.services.util.CreateList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class MasterServiceImpl implements MasterService {
    private final MasterRepo masterRepo;
    private final OrderRepo orderRepo;
    private final TimeSlotRepo timeSlotRepo;

    public MasterServiceImpl(MasterRepo masterRepo, OrderRepo orderRepo, TimeSlotRepo timeSlotRepo) {
        this.masterRepo = masterRepo;
        this.orderRepo = orderRepo;
        this.timeSlotRepo = timeSlotRepo;
    }


    @Override
    public Iterable<Masters> getMasters(String sort) {
        List<Masters> masters = new ArrayList<>();
        masterRepo.findAll().forEach(masters::add);
        switch (sort) {
            case "name":
                masters.sort(Comparator.comparing(Masters::getName));
                break;
            case "rating":
                masters.sort(Comparator.comparing(Masters::getRating).reversed());
                break;
        }
        return masters;
    }


    @Override
    @Transactional
    public List<Orders> getAllRecords(Users users) {
        return orderRepo.findAllByTimeSlotIn(timeSlotRepo.findTime_slotsByMaster(getMasterByUser(users)));
    }

    @Override
    public Masters getMasterByUser(Users user) {
        return masterRepo.findByNameAndSurname(user.getFirstname(), user.getLastname());
    }

    @Override
    public List<Time_slots> getAllTimeSlotsByMaster(Masters masters) {
        return timeSlotRepo.findTime_slotsByMaster(masters);
    }

    @Override
    public CreateList getSchedule(Orders order) {
        List<Time_slots> time_slots = getAllTimeSlotsByMaster(getMasterById(order.getTimeSlot().getMaster().getId()));
        CreateList createList = new CreateList();
        List<LocalDateTime> list = createList.getLocalDateTimes();
        List<LocalDateTime> newList = new ArrayList<>();

        for (LocalDateTime loc : list) {
            for (Time_slots g : time_slots) {
                if ((g.getDate_time().getMonth() == loc.getMonth() && g.getDate_time().getDayOfMonth() == loc.getDayOfMonth() && g.getDate_time().getHour() == loc.getHour())) {
                    newList.add(loc);
                }
            }
        }
        list.removeAll(newList);
        createList.setLocalDateTimes(list);
        return createList;
    }

    @Override
    public Masters getMasterById(Long id) {
        return masterRepo.findById(id).get();
    }

    @Transactional
    public boolean save(Orders orders, Orders order) {
        Time_slots t = orders.getTimeSlot();
        t.setDate_time(order.getTimeSlot().getDate_time());
        orders.setTimeSlot(t);
        orders.setPaymentStatus(Payment_statuses.OPENED);
        orders.setPerformance_status(Performance_statuses.ACTIVE);
        timeSlotRepo.save(orders.getTimeSlot());
        orderRepo.save(orders);
        return true;
    }
}
