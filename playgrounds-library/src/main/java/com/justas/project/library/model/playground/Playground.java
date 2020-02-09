package com.justas.project.library.model.playground;


import com.justas.project.library.model.Child;
import com.justas.project.library.util.CalculationUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;


public interface Playground {

    PlaygroundType getType();

    int getMaxSlots();

    int getId();

    default boolean addChildIntoPlayground(Child child) {
        if (getMaxSlots() > getCurrentKids().size()) {
            return getCurrentKids().add(child);
        } else {
            addIntoQueue(child);
        }
        return false;
    }

    default boolean removeChildFromPlayground(int childId) {
        Optional<Child> child = getCurrentKids().stream().filter(c -> c.getId() == childId).findFirst();
        return child.filter(child1 -> getCurrentKids().remove(child1)).isPresent();
    }

    default boolean addIntoQueue(Child child) {
        return getCurrentQueue().add(child);
    }

    default void addVipIntoQueue(Child child, int skipBy) {
        /*
       maintain a balance of max 1 skip for 3 normal entries ex. if there are 5 waiting,
       and 2 vips come, one would get into front of line,
       second would be after 3 non vip's would enter the play site,
        and total order would be VNNNVNN
         */
        int balance = 4;
        int currentQueueSize = getCurrentQueue().size();
        int putInto = currentQueueSize - skipBy;
        if (putInto < 0) {
            putInto = 0;
        }
        Optional<Child> lastVIP = getCurrentQueue().stream()
                .filter(c -> c.getTicket().isVip())
                .reduce((first, second) -> second);
        if (!lastVIP.isPresent()) {
            getCurrentQueue().add(putInto, child);
        } else {
            int lastVipPosition = getCurrentQueue().indexOf(lastVIP.get());
            if (currentQueueSize - skipBy - balance > 0) {
                getCurrentQueue().add(currentQueueSize - skipBy, child);
            } else if (lastVipPosition + balance < currentQueueSize) {
                getCurrentQueue().add(lastVipPosition + balance, child);
            } else {
                getCurrentQueue().add(child);
            }
        }
    }

    Collection<Child> getCurrentKids();

    LinkedList<Child> getCurrentQueue();

    default double calculateAndReturnUtilization() {
        if (getMaxSlots() == 0) {
            return 100.00;
        }
        if (getCurrentKids().isEmpty()) {
            return 0.00;
        }
        double fullNumber = (1.0 * getCurrentKids().size() / getMaxSlots()) * 100;
        return CalculationUtil.processDouble((fullNumber));
    }

    Map<LocalDateTime, Double> getUtilizationSnapshots();

    default void saveSnapshot() {
        getUtilizationSnapshots().put(LocalDateTime.now(), calculateAndReturnUtilization());
    }
}
