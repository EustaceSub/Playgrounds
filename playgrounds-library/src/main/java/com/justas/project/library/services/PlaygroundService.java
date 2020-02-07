package com.justas.project.library.services;

import com.google.gson.JsonObject;
import com.justas.project.library.mapper.PlaygroundMapper;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.util.ReadingJsonFilesUtil;
import lombok.Getter;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;


public class PlaygroundService {
    private static PlaygroundMapper playgroundMapper = new PlaygroundMapper();
    @Getter
    private static Map<PlaygroundType, List<Playground>> playgrounds;

    /**
     * Loads all playgrounds from playgrounds.json file
     */
    public static void loadPlaygrounds() {

        String fileName = "playgrounds.json";
        JsonObject jsonObject = ReadingJsonFilesUtil.getJsonFromResource(fileName);
        playgrounds = playgroundMapper.mapJsonObject(jsonObject).stream()
                .collect(groupingBy(Playground::getType));
    }
}
