package com.justas.project.library.factory;

import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;

public class UtilizationSnapshotDataFactory {
    public static UtilizationSnapshotData createUtilizationSnapshotDataFromPlayground(Playground playground) {
        return UtilizationSnapshotData.builder()
                .utilizationSnapshots(playground.getUtilizationSnapshots())
                .playgroundType(playground.getType())
                .build();
    }
}
