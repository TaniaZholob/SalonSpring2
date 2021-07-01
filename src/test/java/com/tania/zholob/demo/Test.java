package com.tania.zholob.demo;

import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.entity.Performance_statuses;
import com.tania.zholob.demo.model.entity.Time_slots;
import com.tania.zholob.demo.model.repos.OrderRepo;
import com.tania.zholob.demo.model.repos.TimeSlotRepo;
import com.tania.zholob.demo.model.services.MasterService;
import com.tania.zholob.demo.model.services.serviceImpl.MasterServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

@SpringBootTest
public class Test {

    @Autowired
    private MasterService masterService;


    @MockBean
    private TimeSlotRepo timeSlotRepo;

    @MockBean
    private OrderRepo orderRepo;

    @org.junit.jupiter.api.Test
    public void addOrder() {
        Orders order = new Orders();
        Orders orders = new Orders();
        Time_slots time_slots = new Time_slots();
        time_slots.setDate_time(LocalDateTime.now());
        order.setTimeSlot(time_slots);
        orders.setTimeSlot(new Time_slots());
        Assert.assertTrue(masterService.save(orders, order));
        Assert.assertTrue(CoreMatchers.is(orders.getPerformance_status()).matches(Performance_statuses.ACTIVE));
        Mockito.verify(timeSlotRepo, Mockito.times(1)).save(orders.getTimeSlot());
        Mockito.verify(orderRepo, Mockito.times(1)).save(orders);
    }
}

