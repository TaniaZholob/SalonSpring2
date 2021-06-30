package com.tania.zholob.demo.controllers;

import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Procedures;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.repos.UserRepo;
import com.tania.zholob.demo.model.services.serviceImpl.MasterServiceImpl;
import com.tania.zholob.demo.model.services.serviceImpl.ProcedureServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@Log4j2
public class Controller {

    @Autowired
    private MasterServiceImpl masterService;

    @Autowired
    private ProcedureServiceImpl procedureService;


    @Autowired
    private UserRepo userRepo;


    @GetMapping("/AllMasters")
    public String masters(Model model, @RequestParam(name = "sort", required = false, defaultValue = "sort") String sort) {
        Iterable<Masters> masters = masterService.getMasters(sort);
        System.out.println(masters);
        model.addAttribute("all_masters", masters);
        return "listOfMasters";
    }

    @GetMapping("/allProcedures")
    public String procedures(Model model, @ModelAttribute("filter") String filter) {
        Iterable<Procedures> procedures = procedureService.getProcedures(filter);
        model.addAttribute("filter", "");
        model.addAttribute("procedures", procedures);
        return "listOfProcedures";
    }


}
