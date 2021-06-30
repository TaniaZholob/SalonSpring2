package com.tania.zholob.demo.model.repos;

import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Procedures;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface MasterRepo extends CrudRepository<Masters, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Masters set rating=:rating where id=:id")
    void  updateMasterRating(@Param(value = "id") Long id, @Param(value =  "rating")int rating);

    @Transactional
    Masters findByNameAndSurname(String name, String surname);


//    List<Masters> getAllByProceduresIn(List<Procedures> procedures);

}
