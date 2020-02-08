package com.justas.project.library.model.playground;

import com.justas.project.library.generator.IdGenerator;
import com.justas.project.library.model.Child;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public abstract class PlaygroundBasicImpl implements Playground {
    private final int id = IdGenerator.generatePlaygroundId();
    private Set<Child> currentKids = new HashSet<>();
    private LinkedList<Child> currentQueue = new LinkedList<>();
    private Map<LocalDateTime, Double> utilizationSnapshots = new HashMap<>();

}
