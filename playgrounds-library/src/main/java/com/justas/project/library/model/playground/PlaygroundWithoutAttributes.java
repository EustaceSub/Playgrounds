package com.justas.project.library.model.playground;

public abstract class PlaygroundWithoutAttributes extends PlaygroundBasicImpl {
    @Override
    public double calculateAndReturnUtilization() {
        return getCurrentKids().size() == getMaxSlots() ? 100.0 : 0.0;
    }
}
