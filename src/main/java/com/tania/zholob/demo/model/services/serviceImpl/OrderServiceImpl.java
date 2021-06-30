package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.entity.Payment_statuses;
import com.tania.zholob.demo.model.entity.Performance_statuses;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.repos.OrderRepo;
import com.tania.zholob.demo.model.services.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Page<Orders> getOrders(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public boolean changePayment(Long id) {
        Orders order = findById(id).get();
        if (order.getPaymentStatus().equals(Payment_statuses.OPENED)) {
            orderRepo.confirmPayment(id, Payment_statuses.CONFIRMED);
            return true;
        } else {
            orderRepo.confirmPayment(id, Payment_statuses.CLOSED);
            return true;
        }
//        if (order.getPerformance_status().equals(Performance_statuses.CLOSED)) {
//            orderRepo.confirmPayment(id, Payment_statuses.CLOSED);
//            log.info("Set new payment status: " + Payment_statuses.CLOSED);
//        } else {
//            orderRepo.confirmPayment(id, Payment_statuses.PAID);
//            log.info("Set new payment status: " + Payment_statuses.PAID);
//        }
//        return true;

    }

    @Override
    public List<Orders> findOrders(Users user) {
        return orderRepo.findByUser(user);
    }

    @Override
    @Transactional
    public boolean changePerform(Long id) {
        Orders order = orderRepo.findById(id).get();
        orderRepo.perform(id, Performance_statuses.CLOSED);
//        if (order.getPaymentStatus().equals(Payment_statuses.PAID)) {
//            orderRepo.confirmPayment(id, Payment_statuses.CLOSED);
//        }
        return true;
    }

    @Override
    public Orders save(Orders order) {
        return orderRepo.save(order);
    }
}
