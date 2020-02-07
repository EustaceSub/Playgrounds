package com.justas.project.library.model.playground;

public abstract class PlaygroundWithoutAttributes extends PlaygroundBasic {
    @Override
    public double calculateUtilization() {
        return getCurrentKids().size() == getMaxSlots() ? 100.0 : 0.0;

    }
}
