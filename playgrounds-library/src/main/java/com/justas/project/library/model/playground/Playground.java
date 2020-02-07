package com.justas.project.library.model.playground;


import com.justas.project.library.model.Child;
import com.justas.project.library.util.CalculationUtil;

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

    default double calculateUtilization() {
        if (getMaxSlots() == 0) {
            return 100.00;
        }
        if (getCurrentKids().isEmpty()) {
            return 0.00;
        }
        double fullNumber = (1.0 * getCurrentKids().size() / getMaxSlots()) * 100;
        return CalculationUtil.processDouble((fullNumber));
    }
}
