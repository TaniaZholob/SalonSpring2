package com.tania.zholob.demo.model.services;

import com.tania.zholob.demo.model.entity.Procedures;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProcedureService {
    Iterable<Procedures> getProcedures(String filter);
    Iterable<Procedures> getAllProcedures();
    Procedures findProcedureById(Long id);
}
