package com.justas.project.library.generator;

import lombok.experimental.UtilityClass;

import java.util.concurrent.atomic.AtomicInteger;

@UtilityClass
public class IdGenerator {
    private AtomicInteger playgroundIdGenerator = new AtomicInteger(0);
    private AtomicInteger ticketIdGenerator = new AtomicInteger(0);

    public int generatePlaygroundId() {
        return playgroundIdGenerator.incrementAndGet();
    }

    public int generateTicketId() {
        return ticketIdGenerator.incrementAndGet();
    }
}
