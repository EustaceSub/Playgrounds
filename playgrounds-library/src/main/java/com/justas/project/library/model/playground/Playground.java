package com.justas.project.library.model.playground;


import com.justas.project.library.model.Child;

import java.util.Collection;


public interface Playground {

    PlaygroundType getType();

    int getMaxSlots();

    default boolean addChildIntoPlayground(Child child) {
        if (getMaxSlots() > getCurrentKids().size()) {
            return getCurrentKids().add(child);
        } else {
            addIntoQueue(child);
        }
        return false;
    }

    default boolean removeChildFromPlayground(Child child) {
        return getCurrentKids().remove(child);
    }

    default boolean addIntoQueue(Child child) {
        return getCurrentQueue().add(child);
    }

    Collection<Child> getCurrentKids();

    Collection<Child> getCurrentQueue();
}
