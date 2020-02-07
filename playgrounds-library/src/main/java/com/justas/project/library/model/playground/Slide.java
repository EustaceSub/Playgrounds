package com.justas.project.library.model.playground;


import java.text.NumberFormat;

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
