package com.justas.project.library.model.playground;

import lombok.Getter;

@Getter
public abstract class PlaygroundWithAttributes implements Playground {
    private int amountOfChilds = 0;
    private final int maxSlots;

    PlaygroundWithAttributes(int maxSlots) {
        this.maxSlots = maxSlots;
    }
}
