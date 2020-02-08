package com.justas.project.library.model.playground;

import lombok.Getter;

@Getter
public abstract class PlaygroundWithAttributes extends PlaygroundBasicImpl {
    private int amountOfChilds = 0;
    private final int maxSlots;

    PlaygroundWithAttributes(int maxSlots) {
        super();
        this.maxSlots = maxSlots;
    }
}
