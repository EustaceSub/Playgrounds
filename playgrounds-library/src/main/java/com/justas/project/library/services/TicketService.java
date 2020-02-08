package com.justas.project.library.services;

import com.justas.project.library.model.Ticket;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketService {
    private List<Ticket> simpleTickets = new ArrayList<>();
    private AtomicInteger id = new AtomicInteger(0);

    public int generateSimpleTicket() {
        Ticket ticket = new Ticket(id.incrementAndGet());
        simpleTickets.add(ticket);
        return ticket.getTicketId();
    }
}
