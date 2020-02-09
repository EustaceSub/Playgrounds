package com.justas.project.server.jobs;

import com.justas.project.library.services.PlaygroundService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Log
public class PlaygroundJob {
    private final PlaygroundService playgroundService;

    @Autowired
    public PlaygroundJob(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @PostConstruct
    private void loadPlaygrounds() {
        playgroundService.loadPlaygrounds();
    }

    @Scheduled(cron = "${saveSnapshotsCron}")
    public void loadPlaygroundJob() {
        playgroundService.saveUtilizationSnapshots();
        log.info("saving utilization...");
    }
}
