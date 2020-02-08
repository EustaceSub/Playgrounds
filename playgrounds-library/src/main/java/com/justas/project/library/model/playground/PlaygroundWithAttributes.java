package com.justas.project.library.model.playground;

import lombok.Getter;

@Getter
public abstract class PlaygroundWithAttributes extends PlaygroundBasic {
    private int amountOfChilds = 0;
    private final int maxSlots;

    PlaygroundWithAttributes(int maxSlots) {
        super();
        this.maxSlots = maxSlots;
    }
}
