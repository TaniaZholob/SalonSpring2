package com.tania.zholob.demo.model.repos;

import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Time_slots;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepo extends CrudRepository<Time_slots,Long> {
    List<Time_slots> findTime_slotsByMaster(Masters masters);

    @Transactional
    @Modifying
    @Query("UPDATE Time_slots set date_time=:date_time where id=:id")
    int updateDateTime(@Param(value = "id") Long id, @Param(value =  "date_time") LocalDateTime date_time);

}
