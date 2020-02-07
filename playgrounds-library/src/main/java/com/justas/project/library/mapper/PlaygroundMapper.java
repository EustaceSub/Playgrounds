package com.justas.project.library.mapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.justas.project.library.factory.PlaygroundFactory;
import com.justas.project.library.model.playground.Playground;
import com.justas.project.library.model.playground.PlaygroundType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;


public class PlaygroundMapper implements Mapper<Playground> {

    @Override
    public Collection<Playground> mapJsonObject(JsonObject playgrounds) {
        return Stream.of(PlaygroundType.values())
                .flatMap(type -> createPlaygrounds(playgrounds.get(type.name()), type).stream())
                .collect(toList());
    }

    private static List<Playground> createPlaygrounds(JsonElement specificPlaygroundParams, PlaygroundType type) {
        if (specificPlaygroundParams == null) {
            return emptyList();
        }
        return createPlaygroundsFromJsonElement(specificPlaygroundParams, type);
    }

    /**
     * Creates One type of Playgrounds.
     * If Playground must to have Attributes - takes it from jsonElement.
     *
     * @param jsonElement    - object that contains required information about Playground.
     * @param playgroundType - What kind of Playground to create.
     * @return Collection of one type of Playground with attributes.
     */
    private static List<Playground> createPlaygroundsFromJsonElement(JsonElement jsonElement, PlaygroundType playgroundType) {
        List<Playground> playgrounds = new ArrayList<>();

        if (!playgroundType.isHasAttributes()) {
            int howManyToCreate = jsonElement.getAsJsonObject().get("amount").getAsInt();
            for (int i = 0; i < howManyToCreate; i++) {
                playgrounds.add(PlaygroundFactory.createPlaygroundWithNoParams(playgroundType));
            }
        } else {
            for (JsonElement params : jsonElement.getAsJsonArray()) {
                int maxSpots = params.getAsJsonObject().get("max_spots").getAsInt();
                playgrounds.add(PlaygroundFactory.createPlaygroundWithParams(playgroundType, maxSpots));
            }
        }
        return playgrounds;
    }

}
