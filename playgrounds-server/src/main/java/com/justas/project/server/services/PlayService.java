package com.justas.project.server.services;

import com.justas.project.library.factory.ChildFactory;
import com.justas.project.library.model.Child;
import com.justas.project.library.model.UtilizationSnapshotData;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.services.HistoryService;
import com.justas.project.library.services.PlaygroundService;
import com.justas.project.library.services.TicketService;
import com.justas.project.server.dto.ChildDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Service
public class PlayService {
    private final PlaygroundService playgroundService;
    private final HistoryService historyService;
    private final TicketService ticketService;

    @Autowired
    public PlayService(PlaygroundService playgroundService, HistoryService historyService, TicketService ticketService) {
        this.playgroundService = playgroundService;
        this.historyService = historyService;
        this.ticketService = ticketService;
    }


    public Map<PlaygroundType, List<Playground>> getPlaygrounds() {
        return playgroundService.getPlaygrounds();
    }

    public List<UtilizationSnapshotData> getSnapshots() {
        return playgroundService.getUtilizationSnapshots();
    }

    public ResponseEntity<String> addChildIntoPlayground(int id, ChildDTO childDTO) {
        Playground playground = findPlaygroundById(id);
        boolean isTicketVIP = ticketService.isTicketVIP(childDTO.getTicketId());
        Child child = mapChildFromDTO(childDTO, isTicketVIP);
        boolean isAdded = playground.addChildIntoPlayground(child);
        if (isAdded) {
            historyService.childJoinedIntoPlayground(child.getId(), id);
            return ResponseEntity.ok("Child was added successfully");
        } else {
            return ResponseEntity.ok("Child was NOT added.Playground is full.Child is added into queue");
        }
    }

    public ResponseEntity<String> addChildIntoQUEUEVip(int id, int skipBy, ChildDTO childDTO) {
        Playground playground = findPlaygroundById(id);
        boolean isTicketVIP = ticketService.isTicketVIP(childDTO.getTicketId());
        Child child = mapChildFromDTO(childDTO, isTicketVIP);
        int position = playground.addVipIntoQueue(child, skipBy);
        return ResponseEntity.ok(format("Kid's position in queue is: %s ", position));
    }

    public Playground findPlaygroundById(int id) {
        return playgroundService.findPlaygroundById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playground with given id was not found..."));
    }

    public ResponseEntity<String> deleteChildFromPlayground(int id, int childId) {
        Playground playground = playgroundService.findPlaygroundById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playground with given id was not found..."));
        boolean isRemoved = playground.removeChildFromPlayground(childId);
        if (isRemoved) {
            historyService.childLeftPlayground(childId, id);
            return ResponseEntity.ok("Child was removed successfully");
        } else {
            throw new RuntimeException("Child was not removed from Playground.Please investigate..");
        }
    }

    private Child mapChildFromDTO(ChildDTO childDTO, boolean isVIP) {
        return ChildFactory.createChild(childDTO.getName(), childDTO.getAge(), childDTO.getTicketId(), isVIP);
    }

}
