package com.tania.zholob.demo.controllers;


import com.tania.zholob.demo.model.entity.Orders;
import com.tania.zholob.demo.model.repos.TimeSlotRepo;
import com.tania.zholob.demo.model.services.MasterService;
import com.tania.zholob.demo.model.services.TimeSlotService;
import com.tania.zholob.demo.model.services.serviceImpl.OrderServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@Log4j2
public class AdminController {
    private final OrderServiceImpl orderService;
    private final MasterService masterService;
    private final TimeSlotService timeSlotService;

    public AdminController(OrderServiceImpl orderService, MasterService masterService, TimeSlotService timeSlotService) {
        this.orderService = orderService;
        this.masterService = masterService;
        this.timeSlotService = timeSlotService;
    }

    @Autowired
    TimeSlotRepo timeSlotRepo;

    @GetMapping("/records")
    public String getAllRecords(@PageableDefault(size = 3) Pageable pageable, Model model) {

        model.addAttribute("page", orderService.getOrders(pageable));
        log.debug("Get all records");
        return "admin/records";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        Optional<Orders> order = orderService.findById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            model.addAttribute("allDates", masterService.getSchedule(order.get()));
            model.addAttribute("newDate", order.get().getTimeSlot());
            return "admin/editRecord";
        } else {
            return "admin/records";
        }
    }

    @PostMapping("/save")
    public String save(Orders order) {
        timeSlotService.updateTime(order.getTimeSlot());
        return "redirect:/admin/records";
    }


    @PatchMapping("/{id}/confirmPayment")
    public String changePaymentStatus(@PathVariable("id") Long id) {
        orderService.changePayment(id);
        return "redirect:/admin/records";
    }

}
