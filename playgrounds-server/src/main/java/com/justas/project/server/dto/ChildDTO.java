package com.justas.project.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildDTO {
    private String name;
    private int age;
    private int ticketId;
}
