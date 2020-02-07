package com.justas.project.library.factory;

import com.justas.project.library.model.playground.*;


public class PlaygroundFactory {

    public static Playground createPlaygroundWithNoParams(PlaygroundType playgroundType) {
        switch (playgroundType) {
            case SLIDE:
                return new Slide();
            case DOUBLE_SWINGS:
                return new DoubleSwings();
            default:
                throw new RuntimeException("Bad Playground type for this method..");
        }
    }

    public static Playground createPlaygroundWithParams(PlaygroundType playgroundType, int maxSlots) {
        switch (playgroundType) {
            case CAROUSEL:
                return new Carousel(maxSlots);
            case BALL_PIT:
                return new BallPit(maxSlots);
            default:
                throw new RuntimeException("Bad Playground type for this method..");
        }
    }

}
