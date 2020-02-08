package com.justas.project.server.jobs;

import com.justas.project.library.services.PlaygroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PlaygroundJob {
    final PlaygroundService playgroundService;

    @Autowired
    public PlaygroundJob(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
    }

    @PostConstruct
    private void loadPlaygrounds() {
        playgroundService.loadPlaygrounds();
    }

    @Scheduled(cron = "${saveSnapshotsCron}")
    public void test() {
        playgroundService.saveUtilizationSnapshots();
        System.out.println("saving...");
    }
}
