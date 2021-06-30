package com.tania.zholob.demo.model.repos;

import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Procedures;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepo extends CrudRepository<Procedures,Long> {

}
