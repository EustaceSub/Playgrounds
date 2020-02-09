package com.justas.project.library.model;

import com.justas.project.library.generator.IdGenerator;
import lombok.*;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    private final int id = IdGenerator.generateChildId();
    private String name;
    private int age;
    private Ticket ticket;
}
