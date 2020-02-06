package com.justas.project.library.model.playground;

public class DoubleSwings extends PlaygroundWithoutAttributes {


    @Override
    public PlaygroundType getType() {
        return PlaygroundType.DOUBLE_SWINGS;
    }

    @Override
    public int getMaxSlots() {
        return 2;
    }
}
