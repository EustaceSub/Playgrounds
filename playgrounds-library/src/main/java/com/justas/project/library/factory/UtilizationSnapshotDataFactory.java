package com.justas.project.library.factory;

import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;

public class UtilizationSnapshotDataFactory {
    /**
     * Collects information about playground utilization and type, and creates Object with that data.
     *
     * @param playground - playground, we would like to extract information from.
     * @return - new created {@link UtilizationSnapshotData} with information needed.
     */
    public static UtilizationSnapshotData createUtilizationSnapshotDataFromPlayground(Playground playground) {
        return UtilizationSnapshotData.builder()
                .utilizationSnapshots(playground.getUtilizationSnapshots())
                .playgroundType(playground.getType())
                .build();
    }
}
