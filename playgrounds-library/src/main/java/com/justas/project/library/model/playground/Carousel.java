package com.justas.project.library.model.playground;

import lombok.Getter;

@Getter
public class Carousel extends PlaygroundWithAttributes {

    Carousel(int maxSlots) {
        super(maxSlots);
    }

    @Override
    public PlaygroundType getType() {
        return PlaygroundType.CAROUSEL;
    }

}
