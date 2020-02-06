package com.justas.project.library.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundFactory;
import com.justas.project.library.model.playground.PlaygroundType;
import com.justas.project.library.util.ReadingJsonFilesUtil;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;


public class PlaygroundService {
    @Getter
    private static Map<PlaygroundType, List<Playground>> playgrounds;

    /**
     * Loads all playgrounds from playgrounds.json file
     */
    public static void loadPlaygrounds() {

        String fileName = "playgrounds.json";
        JsonObject jsonObject = ReadingJsonFilesUtil.getJsonFromResource(fileName);
        playgrounds = mapPlaygrounds(jsonObject).stream()
                .collect(groupingBy(Playground::getType));
    }


    private static List<Playground> mapPlaygrounds(JsonObject playgrounds) {

        return Stream.of(PlaygroundType.values())
                .flatMap(type -> createPlaygrounds(playgrounds.get(type.name()), type).stream())
                .collect(toList());
    }

    private static List<Playground> createPlaygrounds(JsonElement specificPlaygroundParam, PlaygroundType type) {
        if (specificPlaygroundParam == null) {
            return emptyList();
        }
        return PlaygroundFactory.createPlaygroundsFromJsonElement(specificPlaygroundParam, type);
    }

}
