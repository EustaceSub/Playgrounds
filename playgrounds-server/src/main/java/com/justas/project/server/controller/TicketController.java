package com.justas.project.server.controller;

import com.justas.project.library.model.Ticket;
import com.justas.project.library.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/simple")
    public int registerSimpleTicket() {
        return ticketService.generateSimpleTicketId();
    }

    @PostMapping(value = "/vip")
    public int registerVIPTicket() {
        return ticketService.generateVipTicket();
    }

    @GetMapping
    public List<Ticket> allTickets() {
        return ticketService.getTickets();
    }
}
