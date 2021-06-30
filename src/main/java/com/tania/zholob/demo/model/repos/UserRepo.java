package com.tania.zholob.demo.model.repos;

import com.tania.zholob.demo.model.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
