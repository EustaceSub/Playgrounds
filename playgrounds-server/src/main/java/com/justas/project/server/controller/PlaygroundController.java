package com.justas.project.server.controller;

import com.justas.project.library.model.Child;
import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.services.HistoryService;
import com.justas.project.library.services.PlaygroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("playgrounds")
public class PlaygroundController {

    private final PlaygroundService playgroundService;
    private final HistoryService historyService;

    @Autowired
    public PlaygroundController(PlaygroundService playgroundService, HistoryService historyService) {
        this.playgroundService = playgroundService;
        this.historyService = historyService;
    }

    @GetMapping
    public Map<PlaygroundType, List<Playground>> getPlaygrounds() {
        return playgroundService.getPlaygrounds();
    }

    @GetMapping(value = "/snapshots")
    public List<UtilizationSnapshotData> getSnapshots() {
        return playgroundService.getUtilizationSnapshots();
    }

    @PostMapping(value = {"/{id}/child"})
    public ResponseEntity<String> addChildIntoPlayground(@PathVariable int id, @RequestBody Child child) {
        Playground playground = playgroundService.findPlaygroundById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playground with given id was not found..."));
        boolean isAdded = playground.addChildIntoPlayground(child);
        if (isAdded) {
            historyService.childJoinedIntoPlayground(child.getId(), id);
            return ResponseEntity.ok("Child was added successfully");
        } else {
            return ResponseEntity.ok("Child was NOT added.Playground is full.Child is added into queue");
        }
    }

    @DeleteMapping(value = {"/{id}/child"})
    public ResponseEntity<String> deleteChildFromPlayground(@PathVariable int id, @RequestBody Child child) {
        Playground playground = playgroundService.findPlaygroundById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playground with given id was not found..."));
        boolean isRemoved = playground.removeChildFromPlayground(child);
        if (isRemoved) {
            historyService.childLeftPlayground(child.getId(), id);
            return ResponseEntity.ok("Child was removed successfully");
        } else {
            throw new RuntimeException("Child was not removed from Playground.Please investigate..");
        }
    }


}
