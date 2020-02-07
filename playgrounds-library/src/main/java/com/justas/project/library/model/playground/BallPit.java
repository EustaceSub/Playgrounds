package com.justas.project.library.model.playground;


public class BallPit extends PlaygroundWithAttributes {

    public BallPit(int maxSlots) {
        super(maxSlots);
    }

    @Override
    public PlaygroundType getType() {
        return PlaygroundType.BALL_PIT;
    }

}
