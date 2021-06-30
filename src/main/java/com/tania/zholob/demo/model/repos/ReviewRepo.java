package com.tania.zholob.demo.model.repos;

import com.tania.zholob.demo.model.entity.Reviews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends CrudRepository<Reviews, Long> {
    @Query("select avg(rating) from Reviews where master_id=:master_id")
    int avg(@Param(value = "master_id") Long master_id);
}
