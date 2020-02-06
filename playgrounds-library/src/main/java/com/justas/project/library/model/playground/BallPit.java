package com.justas.project.library.model.playground;


public class BallPit extends PlaygroundWithAttributes {

    BallPit(int maxSlots) {
        super(maxSlots);
    }

    @Override
    public PlaygroundType getType() {
        return PlaygroundType.BALL_PIT;
    }

}
