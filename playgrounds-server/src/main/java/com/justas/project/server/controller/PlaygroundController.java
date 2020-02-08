package com.justas.project.server.controller;

import com.justas.project.library.model.Child;
import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.services.PlaygroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController(value = "/playgrounds")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    @Autowired
    public PlaygroundController(PlaygroundService playgroundService) {
        this.playgroundService = playgroundService;
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
    public ResponseEntity<String> addChild(@PathVariable int id, @RequestBody Child child) {
        Playground playground = playgroundService.findPlaygroundById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playground with given id was not found..."));
        playground.addChildIntoPlayground(child);
        return ResponseEntity.ok("Child was added successfully");
    }


}
