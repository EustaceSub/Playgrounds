package com.justas.project.library.services;

import com.justas.project.library.model.History;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    @Getter
    private final List<History> historyList = new ArrayList<>();

    /**
     * Saves information that child joined into playground.
     *
     * @param childId      - id of child that joined.
     * @param playgroundId - id of playground.
     */
    public void childJoinedIntoPlayground(int childId, int playgroundId) {
        History history = History.builder()
                .childId(childId)
                .playgroundId(playgroundId)
                .startTime(LocalDateTime.now())
                .build();
        historyList.add(history);
    }

    /**
     * Saves history when child left specific playground and calculates duration.
     *
     * @param childId      - id of child.
     * @param playgroundId - id of playground
     */
    public void childLeftPlayground(int childId, int playgroundId) {
        History joinedHistory = findJoiningAndNotLeavingHistory(childId, playgroundId);
        LocalDateTime leftTime = LocalDateTime.now();
        Duration stayTime = Duration.between( joinedHistory.getStartTime(),leftTime);
        joinedHistory.setEndTime(leftTime);
        joinedHistory.setStaytime(stayTime);
    }

    /**
     * Finds History of child, when he joined specific playground, but never left.
     *
     * @param childId      - id of child
     * @param playgroundId - playgroundId child never left
     * @return - history that contains info: when child joined.
     */
    private History findJoiningAndNotLeavingHistory(int childId, int playgroundId) {
        return historyList.stream()
                .filter(h -> h.getChildId() == childId)
                .filter(h -> h.getPlaygroundId() == playgroundId)
                .filter(h -> h.getEndTime() == null)
                .reduce((h1, h2) -> {
                    throw new IllegalStateException("Multiple history when child never left: " + h1 + ", " + h2);
                })
                .get();

    }

}
