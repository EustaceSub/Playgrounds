package com.justas.project.library.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Child {
    private String name;
    private int age;
    private int ticketId;
}
