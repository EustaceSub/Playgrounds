package com.justas.project.server.controller;

import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.server.dto.ChildDTO;
import com.justas.project.server.services.PlayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("playgrounds")
public class PlaygroundController {

    private final PlayService playService;

    public PlaygroundController(PlayService playService) {
        this.playService = playService;
    }

    @GetMapping
    public Map<PlaygroundType, List<Playground>> getPlaygrounds() {
        return playService.getPlaygrounds();
    }

    @GetMapping(value = "/snapshots")
    public List<UtilizationSnapshotData> getSnapshots() {
        return playService.getSnapshots();
    }

    @PostMapping(value = {"/{id}/child"})
    public ResponseEntity<String> addChildIntoPlayground(@PathVariable int id, @RequestBody ChildDTO childDTO) {
        return playService.addChildIntoPlayground(id, childDTO);
    }

    @DeleteMapping(value = {"/{id}/child/{childId}"})
    public ResponseEntity<String> deleteChildFromPlayground(@PathVariable int id, @PathVariable int childId) {
        return playService.deleteChildFromPlayground(id, childId);
    }


}
