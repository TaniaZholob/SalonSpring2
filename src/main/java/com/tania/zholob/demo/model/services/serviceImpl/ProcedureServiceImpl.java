package com.tania.zholob.demo.model.services.serviceImpl;

import com.tania.zholob.demo.model.entity.Procedures;
import com.tania.zholob.demo.model.repos.ProcedureRepo;
import com.tania.zholob.demo.model.services.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepo procedureRepo;

    public ProcedureServiceImpl(ProcedureRepo procedureRepo) {
        this.procedureRepo = procedureRepo;
    }

    @Override
    public Iterable<Procedures> getProcedures(String filter) {
        List<Procedures> procedures = new ArrayList<>();
        procedureRepo.findAll().forEach(procedures::add);
        switch (filter) {
            case "a":
                procedures = procedures.stream().filter(procedure -> procedure.getPrice() <= 400).collect(Collectors.toList());
                break;
            case "b":
                procedures = procedures.stream().filter(procedure -> (procedure.getPrice() > 400 && procedure.getPrice() <= 600)).collect(Collectors.toList());
                break;
            case "c":
                procedures = procedures.stream().filter(procedure -> procedure.getPrice() > 600).collect(Collectors.toList());
                break;
        }
        return procedures;
    }

    @Override
    public Iterable<Procedures> getAllProcedures() {
        return procedureRepo.findAll();
    }

    @Override
    public Procedures findProcedureById(Long id) {
        return procedureRepo.findById(id).get();
    }


}
