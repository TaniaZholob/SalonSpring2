package com.tania.zholob.demo.controllers;


import com.tania.zholob.demo.model.UserPrincipal;
import com.tania.zholob.demo.model.entity.Masters;
import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.entity.Time_slots;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.services.MasterService;
import com.tania.zholob.demo.model.services.OrderService;
import com.tania.zholob.demo.model.services.ProcedureService;
import com.tania.zholob.demo.model.services.util.CreateList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/record")
@PreAuthorize("hasAuthority('CLIENT')")
public class RecordController {

    private final ProcedureService procedureService;
    private final MasterService masterService;


    public RecordController(ProcedureService procedureService, MasterService masterService) {
        this.procedureService = procedureService;
        this.masterService = masterService;
    }

    @GetMapping("/procedure")
    public String procedure(Model model) {
        model.addAttribute("procedureAll", procedureService.getAllProcedures());
        model.addAttribute("newOrder", new Orders());
        return "client/record/procedure";
    }

    @PostMapping("/master")
    public String master(Model model, Orders order, HttpSession session, Authentication authentication) {
        order.setUser(((UserPrincipal) authentication.getPrincipal()).getUser());
        order.setProcedure(procedureService.findProcedureById(order.getProcedure().getId()));
        model.addAttribute("newOrder", order);
        session.setAttribute("newOrder", order);
        return "client/record/master";
    }


    @PostMapping("/date")
    public String date(Model model, Orders order, HttpSession session, Authentication authentication) {
        Orders orders = (Orders) session.getAttribute("newOrder");
        orders.setTimeSlot(order.getTimeSlot());
        session.setAttribute("newOrder", orders);
        model.addAttribute("newOrder", order);
        model.addAttribute("allDates", masterService.getSchedule(orders));
        return "client/record/date";
    }


    @PostMapping("/setDate")
    public String doOrder(Orders order, HttpSession session) {
        Orders orders = (Orders) session.getAttribute("newOrder");
        masterService.save(orders, order);
        return "redirect:/";
    }

}
