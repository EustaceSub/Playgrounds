package com.justas.project.library.model;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Ticket {
    private int ticketId;
    private boolean vip;
}
