package com.justas.project.library.generator;

import lombok.experimental.UtilityClass;

import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class IdGenerator {
    private final AtomicInteger PLAYGROUND_ID_GENERATOR = new AtomicInteger(0);
    private final AtomicInteger TICKET_ID_GENERATOR = new AtomicInteger(0);
    private final AtomicInteger CHILD_ID_GENERATOR = new AtomicInteger(0);

    public int generatePlaygroundId() {
        return PLAYGROUND_ID_GENERATOR.incrementAndGet();
    }

    /**
     * Generates VIP or Simple ticket ID
     *
     * @return - id of new created ticket;
     */
    public int generateTicketId() {
        return TICKET_ID_GENERATOR.incrementAndGet();
    }

    public int generateChildId() {
        return CHILD_ID_GENERATOR.incrementAndGet();
    }
}
