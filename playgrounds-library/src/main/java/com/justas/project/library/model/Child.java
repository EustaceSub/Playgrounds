package com.justas.project.library.model;

import lombok.*;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    private String name;
    private int age;
    private int ticketId;
}
