package com.justas.project.library;

import com.justas.project.library.model.Child;

import java.util.function.Supplier;

import static java.util.UUID.randomUUID;

public class TestUtil {
    protected Supplier<Child> generateChild = () -> Child.builder()
            .name(randomUUID().toString())
            .age(10)
            .ticketId(1)
            .build();
}
