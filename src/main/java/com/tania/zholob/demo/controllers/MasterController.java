package com.tania.zholob.demo.controllers;


import com.tania.zholob.demo.model.UserPrincipal;
import com.tania.zholob.demo.model.services.MasterService;
import com.tania.zholob.demo.model.services.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/master")
@PreAuthorize("hasAuthority('MASTER')")
public class MasterController {
    private MasterService masterService;
    private OrderService orderService;

    public MasterController(MasterService masterService, OrderService orderService) {
        this.masterService = masterService;
        this.orderService = orderService;
    }

    @GetMapping("/schedule")
    public String schedule(Authentication authentication, Model model){


//        System.out.println( masterService.getAllRecords(((UserPrincipal)authentication.getPrincipal()).getUser()));

        model.addAttribute("masterOrder", masterService.getAllRecords(((UserPrincipal)authentication.getPrincipal()).getUser()));
        return "master/schedule";
    }

   @GetMapping("/{id}/perform")
    public String perform(@PathVariable Long id){
        orderService.changePerform(id);

        return "redirect:/master/schedule";
   }
}
