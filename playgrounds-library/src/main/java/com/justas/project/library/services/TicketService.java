package com.justas.project.library.services;

import com.justas.project.library.generator.IdGenerator;
import com.justas.project.library.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/*
This class should be used to do any actions with Tickets;
 */
public class TicketService {
    private List<Ticket> tickets = new ArrayList<>();

    /**
     * Creates and saves new Simple ticket
     *
     * @return - id of new created simple ticket
     */
    public int generateSimpleTicketId() {
        Ticket ticket = new Ticket(IdGenerator.generateTicketId(), false);
        tickets.add(ticket);
        return ticket.getTicketId();
    }

    /**
     * Generates and saves new VIP ticket
     *
     * @return - id of new created VIP ticket
     */
    public int generateVipTicket() {
        Ticket ticket = new Ticket(IdGenerator.generateTicketId(), true);
        tickets.add(ticket);
        return ticket.getTicketId();
    }

    /**
     * Checks if ticket belongs to VIP
     *
     * @param ticketId - ticket id
     * @return - true if ticket is created as VIP
     */
    public boolean isTicketVIP(int ticketId) {
        return tickets.stream().anyMatch(t -> t.getTicketId() == ticketId && t.isVip());
    }
}
