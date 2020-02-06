package com.justas.project.library.model.playground;

import lombok.Getter;

public enum PlaygroundType {
    SLIDE(false), DOUBLE_SWINGS(false), CAROUSEL(true), BALL_PIT(true);
    @Getter
    private boolean hasAttributes;

    PlaygroundType(boolean hasAttributes) {
        this.hasAttributes = hasAttributes;
    }
}
