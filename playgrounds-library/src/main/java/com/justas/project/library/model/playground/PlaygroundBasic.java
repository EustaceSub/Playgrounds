package com.justas.project.library.model.playground;

import com.justas.project.library.model.Child;
import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Getter
public abstract class PlaygroundBasic implements Playground {
    private Set<Child> currentKids = new HashSet<>();
    private LinkedList<Child> currentQueue = new LinkedList<>();
}
