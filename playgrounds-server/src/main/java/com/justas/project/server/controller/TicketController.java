package com.justas.project.server.controller;

import com.justas.project.library.services.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @PostMapping(value = "/simple")
    public int registerSimpleTicket() {
        return TicketService.generateSimpleTicketId();
    }

    @PostMapping(value = "/vip")
    public int registerVIPTicket() {
        return TicketService.generateVipTicket();
    }
}
