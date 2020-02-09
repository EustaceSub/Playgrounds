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

    @GetMapping(value = "/{playgroundId}")
    public Playground getPlayground(@PathVariable int playgroundId) {
        return playService.findPlaygroundById(playgroundId);
    }

    @GetMapping
    public Map<PlaygroundType, List<Playground>> getPlaygrounds() {
        return playService.getPlaygrounds();
    }


    @GetMapping(value = "/snapshots")
    public List<UtilizationSnapshotData> getSnapshots() {
        return playService.getSnapshots();
    }

    @PostMapping(value = {"/{playgroundId}/child"})
    public ResponseEntity<String> addChildIntoPlayground(@PathVariable int playgroundId, @RequestBody ChildDTO childDTO) {
        return playService.addChildIntoPlayground(playgroundId, childDTO);
    }

    @PostMapping(value = {"/{id}/child/vip/{skipBy}"})
    public ResponseEntity<String> addChildIntoPlaygroundVIPQueueWithSkipping(@PathVariable int id,
                                                                             @PathVariable int skipBy,
                                                                             @RequestBody ChildDTO childDTO) {
        return playService.addChildIntoQUEUEVip(id, skipBy, childDTO);
    }

    @DeleteMapping(value = {"/{id}/child/{childId}"})
    public ResponseEntity<String> deleteChildFromPlayground(@PathVariable int id, @PathVariable int childId) {
        return playService.deleteChildFromPlayground(id, childId);
    }
}
