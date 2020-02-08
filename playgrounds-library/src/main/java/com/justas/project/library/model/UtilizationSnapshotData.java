package com.justas.project.library.model;

import com.justas.project.library.model.playground.PlaygroundType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class UtilizationSnapshotData {
    private Map<LocalDateTime, Double> utilizationSnapshots = new HashMap<>();
    private PlaygroundType playgroundType;
}
