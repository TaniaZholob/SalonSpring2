package com.tania.zholob.demo.model.services;

import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.entity.Users;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface OrderService {
    Page<Orders> getOrders(Pageable pageable);

   Optional<Orders> findById(Long id);

    boolean changePayment(Long id);

    List<Orders> findOrders(Users user);

    boolean changePerform(Long id);

    Orders save(Orders order);
}
