package com.justas.project.library;

import com.justas.project.library.model.Child;
import com.justas.project.library.model.Ticket;
import com.justas.project.library.services.TicketService;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;

public class TestUtil {
    private final TicketService ticketService = new TicketService();

    private final Function<Ticket, Child> generateChild = (ticket) ->
            Child.builder()
                    .name(randomUUID().toString())
                    .age(10)
                    .ticket(ticket)
                    .build();

    protected final Supplier<Child> generateCommonChild = () -> {
        int ticketId = ticketService.generateSimpleTicketId();
        return generateChild.apply(new Ticket(ticketId, false));
    };
    protected final Supplier<Child> generateVIPChild = () -> {
        int ticketId = ticketService.generateVipTicket();
        return generateChild.apply(new Ticket(ticketId, true));
    };


}
