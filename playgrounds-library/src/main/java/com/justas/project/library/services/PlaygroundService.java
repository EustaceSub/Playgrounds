package com.justas.project.library.services;

import com.google.gson.JsonObject;
import com.justas.project.library.mapper.PlaygroundMapper;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.util.ReadingJsonFilesUtil;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Log
public class PlaygroundService {
    private PlaygroundMapper playgroundMapper = new PlaygroundMapper();
    @Getter
    private Map<PlaygroundType, List<Playground>> playgrounds = Stream.of(
            new AbstractMap.SimpleEntry<>(PlaygroundType.BALL_PIT, new ArrayList<Playground>()),
            new AbstractMap.SimpleEntry<>(PlaygroundType.CAROUSEL, new ArrayList<Playground>()),
            new AbstractMap.SimpleEntry<>(PlaygroundType.SLIDE, new ArrayList<Playground>()),
            new AbstractMap.SimpleEntry<>(PlaygroundType.DOUBLE_SWINGS, new ArrayList<Playground>())
    ).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    /**
     * Loads all playgrounds from playgrounds.json file
     */
    public void loadPlaygrounds() {

        String fileName = "playgrounds.json";
        JsonObject jsonObject = ReadingJsonFilesUtil.getJsonFromResource(fileName);
        playgroundMapper.mapJsonObject(jsonObject).forEach(this::registerPlayground);
    }

    public void registerPlayground(Playground playground) {
        playgrounds.get(playground.getType()).add(playground);
    }

    public long calculateVisitorsCount() {
        return playgrounds.entrySet().stream()
                .flatMap(e -> e.getValue().stream())
                .mapToLong(p -> p.getCurrentKids().size())
                .sum();
    }
}
