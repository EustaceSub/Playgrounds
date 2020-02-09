package com.justas.project.library.model.playground;

import lombok.Getter;

@Getter
abstract class PlaygroundWithAttributes extends PlaygroundBasicImpl {
    private int amountOfChildren = 0;
    private final int maxSlots;

    PlaygroundWithAttributes(int maxSlots) {
        super();
        this.maxSlots = maxSlots;
    }
}
