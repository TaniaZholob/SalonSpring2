package com.tania.zholob.demo.controllers;


import com.tania.zholob.demo.model.UserPrincipal;
import com.tania.zholob.demo.model.entity.Reviews;
import com.tania.zholob.demo.model.entity.Users;
import com.tania.zholob.demo.model.services.OrderService;
import com.tania.zholob.demo.model.services.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/client")
@PreAuthorize("hasAuthority('CLIENT')")
public class UserController {
    private final OrderService orderService;
    private final ReviewService reviewService;

    public UserController(OrderService orderService, ReviewService reviewService) {
        this.orderService = orderService;
        this.reviewService = reviewService;
    }

    @GetMapping("/accountPage")
    public String getAccountPage(Authentication authentication, Model model) {
        Users user = ((UserPrincipal) authentication.getPrincipal()).getUser();
        model.addAttribute("orders", orderService.findOrders(user));
        model.addAttribute("user", user);
        return "client/accountPage";
    }

    @GetMapping("/{id}/review")
    public String review(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderService.findById(id).get());
        model.addAttribute("reviewO", new Reviews());
        return "client/review";
    }

    @PostMapping("/addReview/{idMaster}")
    public String addReview(@ModelAttribute("reviewO") Reviews review, @PathVariable Long idMaster, Authentication authentication) {

        reviewService.saveReview(review, idMaster, ((UserPrincipal) authentication.getPrincipal()).getUser().getId());
        return "redirect:/client/accountPage";
    }

    @GetMapping("/{idOrder}/paid")
    public String paid(@PathVariable Long idOrder) {

        orderService.changePayment(idOrder);
        return "redirect:/client/accountPage";
    }
}
