package com.tania.zholob.demo.model.repos;


import com.tania.zholob.demo.model.entity.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends PagingAndSortingRepository<Orders, Long> {

    @Transactional
    @Modifying
    @Query("update Orders set paymentStatus = :paymentStatus where id = :id")
    void confirmPayment(@Param(value = "id") Long id, @Param(value = "paymentStatus") Payment_statuses paymentStatus);


    @Transactional
    @Modifying
    @Query("update Orders set performance_status = :performance_status where id = :id")
    void perform(@Param(value = "id") Long id, @Param(value = "performance_status") Performance_statuses performance_status);




    List<Orders> findByUser(Users user);

    List<Orders> findAllByTimeSlotIn(List<Time_slots> time_slots);
}