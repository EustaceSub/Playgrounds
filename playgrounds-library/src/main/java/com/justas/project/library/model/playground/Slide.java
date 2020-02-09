package com.justas.project.library.model.playground;

public class Slide extends PlaygroundWithoutAttributes {
    @Override
    public PlaygroundType getType() {
        return PlaygroundType.SLIDE;
    }

    @Override
    public int getMaxSlots() {
        return 1;
    }

}
