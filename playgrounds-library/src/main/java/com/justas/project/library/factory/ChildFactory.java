package com.justas.project.library.factory;

import com.justas.project.library.model.Child;
import com.justas.project.library.model.Ticket;

public class ChildFactory {
    /**
     * Creates new Child with given params
     *
     * @param name     - name of Child
     * @param age      - age of child
     * @param ticketId - ticket ID that belong to child
     * @param vip      - is ticket VIP
     * @return - new  {@link Child}
     */
    public static Child createChild(String name, int age, int ticketId, boolean vip) {
        return Child.builder()
                .age(age)
                .name(name)
                .ticket(
                        Ticket.builder()
                                .ticketId(ticketId)
                                .vip(vip)
                                .build())
                .build();
    }
}
